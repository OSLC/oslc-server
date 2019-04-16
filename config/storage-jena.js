/*
 * Copyright 2014 IBM Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/*
 * storage.js implements the abstract ldp-service/storage.js module by
 * storing RDF graphs in Apache Jena. Each document representations
 * an RDF graph.
 *
 * This implementation delegates all the database requests to Fuesiki 
 * using the JenaURL from the env parameter
 * 
 * env configuration parameters are:
 *  * env.jenaURL - the URL of the Fuseki data source
 */

// The abstract storage services this module instantiates
var storage_services = require('oslc-service/storage')

module.exports = storage_services;

var ldp = require('oslc-service/vocab/ldp.js') // LDP vocabulary
var rdflib = require('rdflib')
var request = require('request')
var db

// Some convenient namespaces
var RDF = rdflib.Namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#")
var RDFS = rdflib.Namespace("http://www.w3.org/2000/01/rdf-schema#")
var LDP = rdflib.Namespace('http://www.w3.org/ns/ldp#')

/*
 * Initialize the storage services. This will be database specific.
 * It is this method that determines how the database is organized
 * into LDPCs, and what kind of LDPC is used.
 */ 
storage_services.init = function(env, callback) {
  db = env.jenaURL
  callback()
}

storage_services.reserveURI = function(uri, callback) {
  // simply create a document with only a URI. we will 
  // just update it later on put
  // if it fails, we reject the uri
  var options = {
    uri: db+"data?graph="+uri,
    method: "PUT",
    headers: {
      'Content-Type': 'application/ld+json'
    },
    body: "{}"
  }
  request(options, function(err, ires, body) {
    if (err) {
      console.log(err.stack)
      callback(500)
      return
    }
    callback(ires.statusCode)
  })
}

storage_services.releaseURI = function(uri) {
  var options = {
    uri: db+"data?graph="+uri,
    method: "DELETE",
  }
  request(options, function(err, ires, body){
    if (err) console.log(`Cannot delete ${uri}, error: ${err}`);
  })
}

storage_services.read = function(uri, callback) {
  var options = {
    uri: db+"data?graph="+uri,
    method: "GET",
    headers: {
      "Accept": "text/turtle"
    }      
  }
  request(options, function(err, ires, body) {
    if (err) {
      console.log(err.stack)
      callback(500)
      return
    } else if (ires.statusCode != 200) {
      callback(ires.statusCode)
      return
    }
    // parse the response for the KB
    document = new rdflib.IndexedFormula()
    rdflib.parse(body, document, uri, 'text/turtle', function(err, document) {
      if (err) {
        console.log(err.stack)
        callback(err)
        return
      }
      // set the interaction model
      var interactionModel = null
      document.uri = uri // The container URL
      var uriSym = document.sym(uri)
      if (document.statementsMatching(uriSym, RDF('type'), LDP('BasicContainer')).length !==0) interactionModel = LDP('BasicContainer').value
      if (document.statementsMatching(uriSym, RDF('type'), LDP('DirectContainer')).length !==0) interactionModel = LDP('DirectContainer').value
      document.interactionModel = interactionModel
      if (document.interactionModel === ldp.DirectContainer) {
        var statement = document.any(uriSym, LDP("membershipResource"))
        if (statement) document.membershipResource = statement.value
        statement = document.any(uriSym, LDP("hasMemberRelation"))
        if (statement) document.hasMemberRelation = statement.value
      }
      callback(ires.statusCode, document)
    }) 
  })
}

storage_services.update = function(resource, callback) {
  rdflib.serialize(resource.sym(resource.uri), resource, "none:", "text/turtle", function(err, content) {
    if (err) {
      console.log(err.stack)
      callback(500)
      return
    }
    var options = {
      uri: db+"data?graph="+resource.uri,
      method: "PUT",
      headers: {
         'Content-Type': 'text/turtle'
      },
      body: content
    }
    request(options, function(err, ires, body) {
      if (err) {
        console.log(err.stack)
        callback(500)
        return
      }
      callback(ires.statusCode);
    })
  })
}

/*
 * Insert data (an IndexedFormula) into an existing resource.
 * Could be useful to implement HTTP PATCH.
 *
 * @param {IndexedFormula} data - the triples to insert
 * @param {URI} uri - URI of the resource to insert the triples into
 * @param {callback} callback(status)
 */
storage_services.insertData = function insertData(data, uri, callback) {
  let content = '';
  let statements = data.statementsMatching(undefined, undefined, undefined);
  for (let s of statements) {
    content = content + `<${s.subject.uri}> <${s.predicate.uri}> <${s.object.uri}>. `
  }
  content = 'INSERT DATA {GRAPH <'+uri+'> {'+content+'}}';
  var options = {
    uri: db+"update",
    method: "POST",
    headers: {
       'Content-Type': 'application/sparql-update'
    },
    body: content
  }
  request(options, function(err, ires, body) {
    if (err) {
      console.log(`insertData error: ${err}, body: ${body}`)
      callback(500)
      return
    }
    callback(ires.statusCode);
  })
}



storage_services.remove = function(uri, callback) {
  var options = {
    uri: db+"data?graph="+uri,
    method: "DELETE",
  }
  request(options, function(err, ires, body) {
    if (err) {
      console.log(err.stack)
      callback(500)
      return
    }
    callback(ires.statusCode);
  })
}

/* Get the membership triples for a DirectContainer */
storage_services.getMembershipTriples = function(container, callback) {
  var options = {
    uri: db+"sparql",
    method: "POST",
    headers: {
      "Content-Type": "application/sparql-query",
      "Accept": "application/sparql-results+json"
    },
    body: "SELECT ?member FROM <"+container.membershipResource+"> WHERE {<"+container.membershipResource+"> <"+container.hasMemberRelation+"> ?member .}"      
  }
  request(options, function(err, ires, body) {
    if (err) {
      console.error(`Error geting membership triples: ${err}`);
      callback(500);
      return
    } else if (ires.statusCode !== 200) {
      callback(ires.statusCode)
      return
    }
    callback(ires.statusCode, JSON.parse(body).results.bindings)
  })
}


storage_services.drop = function(callback) {
}


storage_services.query = function(ast, base, callback) {
  // convert OSLC query to SPARQL and execute
  var node = ast
  var stack = new Array()
  var var_stack = new Array()

  if (node.right.val === "*") { 
    var uri = "SELECT ?g WHERE {GRAPH ?g { ?s ?p ?o } }"
    var options = {
      uri: db+"query?query="+uri,
      method: "GET",
      headers: {
        "Accept": "application/sparql-results+json"
      }         
    }

    request(options, function(err, ires, body) {
      console.log("REQUEST " + options.uri)
      callback(err, ires);
    })
  } else {
    var sparql_query_select = "SELECT ?g ";
    var sparql_query_where = "WHERE { GRAPH ?g { ";
    var sparql_query_prefix = "";
    var sparql_query_orderBy = "";

    var found =  {
      "oslc.select": false,
      "oslc.where": false,
      "oslc.orderBy": false,
      "oslc.prefix": false
    }

    while (node.left != null && node.right != null) {
      while(node.left != null){
        stack.push(node)
        node = node.left
      }

      if (node.val === "oslc.select") {
        found["oslc.where"] = false
        found["oslc.orderBy"] = false
        found["oslc.prefix"] = false
        found["oslc.select"] = true
        var_stack.push("?s")
        node = node.pop().right
      } else if(node.val === "oslc.where") {
        found["oslc.select"] = false
        found["oslc.orderBy"] = false
        found["oslc.prefix"] = false
        found["oslc.where"] = true
        node = stack.pop().right
      } else if(node.val === "oslc.prefix") {
        found["oslc.select"] = false
        found["oslc.orderBy"] = false
        found["oslc.where"] = false
        found["oslc.prefix"] = true
        node = stack.pop().right
      } else if(node.val === "oslc.orderBy") {
        found["oslc.select"] = false
        found["oslc.where"] = false
        found["oslc.prefix"] = false
        found["oslc.orderBy"] = true
        node = stack.pop().right
      } else if(node.val === "oslc.searchTerms") {
        found["oslc.searchTerms"] = true
      } else {
        if (found["oslc.prefix"]){
          sparql_query_prefix+="PREFIX "+node.val+": "
          node = stack.pop().right
          sparql_query_prefix+=node.val+" "
          node = stack.pop().right
        }
        if (found["oslc.orderBy"]) {
          if (node.val.charAt(0) === '-') {
            sparql_query_orderBy += "DESC(?"+node.val.substring(1, node.val.length).replace(':','_') + ") "
          } else if(node.val.charAt(0) === '+') {
            sparql_query_orderBy += "ASC(?"+node.val.substring(1, node.val.length).replace(':','_') + ") "
          } else {
            sparql_orderBy += "?"+query.substring(index, i).replace(':','_')+" "
          }
          node = stack.pop().right
        }

        if (found["oslc.select"]) {
          sparql_query_select += "?"+node.val.replace(':','_')+" "
          sparql_query_where += var_stack[stack.length-1]+ " " + node.val + " ?" + node.val.replace(':', '_') + " . "
          var new_var = node.val.replace(':','_')
          node = stack.pop()

          /*
            if (node.val === '{') {
              var_stack.push(new_var)
            }

            if (node.val === '}') {
              if (var_stack[var_stack.length-1] !== "?s") {
                var_stack.pop()  
              } else {
                callback("Syntax Error")
              }
            }
          */

          node = node.right          
        }

        if (found["oslc.where"]) {
          sparql_query_where += "?s " + node.val + " "
          node = stack.pop()
          sparql_query_where += node.right.val + " . "

          // node = stack.pop().right
          node = node.right

          // DOES NOT consider filters
        }

      }

    }

    uri = sparql_query_prefix+sparql_query_select+sparql_query_where+sparql_query_orderBy + "} }"

    var options = {
      uri: db+"query?query="+uri,
      method: "GET",
      headers: {
        "Accept": "application/sparql-results+json"
      }        
    }

    request(options, function(err, ires, body) {
      console.log("REQUEST " + options.uri);
      if (err) {
        callback(err)
        return
      }

      var triples = []
      body = JSON.parse(body)
      if (body["results"]["bindings"].length === 0) {
        callback(err, ires);
      } else {
        for (var i = 0; i < JSON.parse(body)["results"]["bindings"].length; i++) {
          triples.push({"subject": base, "predicate": rdf.resource, "object": body["results"]["bindings"][i]["uri"]})
        }

        jsonld.serialize(triples, function(err, result){
          ires.body = result
          callback(err, ires)
        })
      }             
    })
  }
}
