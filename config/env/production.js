var path = require("path");

module.exports = {
	// Production configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/r",
	"mongoURL": "mongodb://localhost:27017/ldp",
	"JenaURL": "http://169.46.145.70:3030/o3zl9i/",
	"services": path.resolve("./config/defaultServices.json"),
	"dbType": "Jena",
	"contentType": "JSON"
}; 