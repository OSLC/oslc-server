# oslc-server

[![Discourse status](https://img.shields.io/discourse/https/meta.discourse.org/status.svg)](https://forum.open-services.net/)
[![Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/OSLC/chat)

An OSLC Server Node.js app

oslc-server is a Node.js Express.js sample app that uses the oslc-service (which in turn uses the ldp-service) to support a generic OSLC3 server reference implementation. The server is configured to support the OSLC Change Management 3.0 (CM3) domain. This server can be used for OSLC Core 3.0 (OSLC3) and CM3 testing.

oslc-server only provides OSLC and LDP REST services, it does not provide any additional application capabilities or UI. The oslc-server REST services are intended to be used programmatically, possibly to support an OSLC3 and CM3 test server and reference implementation, or by the oslc-browser sample application.

As a result, there are fiew views or controllers with this Express app, rather the REST services are all directly provided by OSLC, LDP and HTTP.

oslc-server exploits the dynamic and asynchronous capabilities of JavaScript and Node.js to build an OSLC server that can easily adapt to any OSLC domain, extensions to domains, and/or integrations between domains, and can be easily adapted to provide OSLC access to existing data sources. 

## Installation

At this time, [oslc-service](https://github.com/OSLC/oslc-service) is not in the npm package manager. The services must be linked into the same directory as oslc-server from GitHub.

Within oslc-server and oslc-service, run 
	
	$ npm install

## Configuration

### Server configuration

At startup, the server uses the config/env.js which in turn uses config/env/development.js, production.js or test.js file to configure the oslc-service Express middleware. The process.env.NODE_ENV environment variable is used to specify which configuration to use: development (the default), production or test. development.js configuration information includes:

	var path = require("path");

	module.exports = {
		// Development configuration settings
		"scheme": "http",
		"host": "localhost",
		"port": 3000,
		"context": "/",
		"JenaURL": "http://localhost:3030/univ/",
		"services": path.resolve("./services/spc.ttl"),
		"contentType": "text/turtle"
	};


### OSLC vocabularies and services configuration

When oslc-server is run for the first time, a set of resources contained in the file indicated by the "services" key in the configuration file is loaded into the configured database in order to establish the initially supported vocabularies and OSLC services. Every subsequent oslc-server startup will reuse these same vocabularies and services, unless the user deletes the root resource.

The following OSLC methods can be used on the server:

- CRUD Methods using RDF resource representations
- Discovery
- Creation Factory 
- Delegated Creation and Selection Dialogs 
- Resource Preview 
- Query Capability


The 'path' package allows the server to read in the services file defaultServices.json, which is a JSON-LD file that contains the services defined for this server. This can be customized depending on the location of the user's preferred default service.

All this information is processed by the oslc-service and ldp-service. The oslc-server only addresses where the configuration information comes from and how it is passed to the services, like any Express middleware configuration.

oslc-server accepts RDF resources in JSON-LD, Turtle, and RDF-XML formats. When conducting OSLC Queries, the results return in OSLC Query Capability RDF/XML resources.


## Starting up the Fuseki server


OSLC Server requires the installation of oslc-service, and Apache Jena Fuseki 3.8.

Apache Jena Fuseki 3.8 must be running before initializing the server. The database can be downloaded [here](https://jena.apache.org/download/#jena-fuseki). Once it is unzipped, enter the folder apache-jena labeled apache-jena-fuseki-3.8.0 and run the following code:

```
cd ~/bin/apache-jena-fuseki-3.8.0
./fuseki-server --update --config=../config-univ.ttl
./fuseki-server --update --config=<location of fuseki config.ttl file>
```

## Running The Server

In oslc-server folder, run the server by executing
	
	$ node app.js

By default, the server can be accessed at http://localhost:3000/.

The server can be exercised using Chrome, Postman, Firefox REST Client, Poster or any other REST client.


## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

