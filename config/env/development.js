var path = require("path");

module.exports = {
	// Development configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/",
	"storageImpl": "ldp-service-jena",
	"jenaURL": "http://localhost:3030/univ/",

	"URL": "http://localhost:3030/ldp/",
	"services": path.resolve("./config/defaultServices.json"),
	"contentType": "JSON",
	"resources": "change-requests"
}; 
