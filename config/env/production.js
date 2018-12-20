var path = require("path");

module.exports = {
	// Production configuration settings
	"scheme": "https",
	"host": "localhost",
	"port": 9443,
	"context": "/",
	"services": path.resolve("./config/defaultServices.json"),
	"contentType": "application/json"
}; 