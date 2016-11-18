# oslc-server
An OSLC Server Node.js app

oslc-server is a Bluemix Node.js app that uses the oslc-service (which in turn uses the ldp-service) to support a generic OSLC3 server reference implementation. The server is configured to support the OSLC Change Management 3.0 (CM3) domain. This server can be used for OSLC Core 3.0 (OSLC3) and CM3 testing.

oslc-server only provides OSLC and LDP REST services, it does not provide any additional application capabilities or UI. The oslc-server REST services are intended to be used programmatically, possibly to support an OSLC3 and CM3 test server and reference implementation

As a result, there are fiew views or controllers with this Express app, rather the REST services are all directly provided by OSLC, LDP and HTTP.

oslc-server exploits the dynamic and asynchronous capabilities of JavaScript and Node.js to build an OSLC server that can easily adapt to any OSLC domain, extensions to domains, and/or integrations between domains, and can be easily adapted to provide OSLC access to existing data sources. 

## Configuration

At startup, the server reads a config.json file and provides configuration information to the oslc-service and ldp-service Express middleware. This configuration information includes:

var path = require("path");

module.exports = {
	// Development configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/r",
	"JenaURL": "http://localhost:3030/ldp/",
	"services": path.resolve("./config/defaultServices.json")
};

The 'path' package allows the server to read in the services file. defaultServices.json is a JSON-LD file that contains the services defined for this server.

All this information is processed by the oslc-service and ldp-service. The oslc-server only addresses where the configuration information comes from and how it is passed to the services, like any Express middleware configuration.

The server can be exercised using Firefox REST Client, Poster or any other REST client.

## Set Up

OSLC Server requires the installation of ldp-service-jena, oslc-service, and Apache Jena Fuseki 2.4.

Apache Jena Fuseki 2.0 can be downloaded [here](https://jena.apache.org/download/#jena-fuseki). Once it is unzipped, enter the folder apache-jena labeled apache-jena-fuseki-2.4.0 and run the following code:

	$ fuseki-server --update --loc=<location of direction> /ldp


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

