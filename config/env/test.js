var path = require("path");

module.exports = {
	// Testing configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/r",
	"mongoURL": "mongodb://localhost:27017/ldp",
	"JenaURL": "http://localhost:3030/ldp/",
	"services": path.resolve("./config/defaultServices.json"),
	"dbType": "Jena",
	"contentType": "JSON"
}; 