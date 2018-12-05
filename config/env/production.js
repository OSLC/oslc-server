var path = require("path");

module.exports = {
	// Production configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/",
	"URL": "http://localhost:3030/ldp/",
	"services": path.resolve("./config/defaultServices.json"),
	"contentType": "JSON",
	"resources": "change-requests"
}; 