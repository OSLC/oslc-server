var path = require("path");

module.exports = {
	// Development configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/",
	"storageImpl": "storage-jena",
	"jenaURL": "http://localhost:3030/univ/",

	"services": path.resolve("./services/spc.ttl"),
	"contentType": "text/turtle"
}; 
