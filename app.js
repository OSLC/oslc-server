/*
 * oslc-server app.js:
 * An OSLC3 server reference implementation that uses oslc-service
 * Express middleware. The server supports the Change Management 3.0 specification.
 *
 * Environment, server configuration and provided services are configured
 * in ./config/env.js
 * 
 * Express middleware configuration is in ./config/express.js
 */

// The NODE_ENV environment variable may not be set, default to development
process.env.NODE_ENV = process.env.NODE_ENV || 'development';
var env = require('./config/env');

// Load, configure and initialize the Express module given its configuration
var app = require('./config/express')(env);

// Anything not handled by the above is an error, nothing should get here
app.use(function(err, req, res, next){
	console.error(err.stack);
	res.send(500, 'oslc-server could not handle the request');
});

app.listen(env.port, env.host);
module.exports = app;
console.log('oslc-server running on port:'+env.port);
