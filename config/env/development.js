var path = require("path");

module.exports = {
	// Development configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/",
	"storageImpl": "ldp-service-jena",
	"jenaURL": "http://localhost:3030/univ/",

	"services": path.resolve("./example/oslc.ttl"),
	"contentType": "text/turtle"
}; 
