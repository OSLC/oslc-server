var path = require("path");

module.exports = {
	// Development configuration settings
	"scheme": "http",
	"host": "localhost",
	"port": 3000,
	"context": "/r",
	"URL": "http://localhost:3030/ldp/",
	"services": path.resolve("./config/defaultServices.json"),
	"contentType": "JSON",
	"resources": "change-requests"
}; 

// http://169.46.145.70:3030/o3zl9i/