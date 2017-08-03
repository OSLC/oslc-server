/*
 * oslc-server:
 * An OSLC3 server reference implementation that uses oslc-service and ldp-service
 * Express middleware. The server supports the Change Management 3.0 specification.
 *
 * Environment, server configuration and provided services are configured
 * in env.js
 */

var oslcService = require('oslc-service');

// The NODE_ENV environment variable is often not properly set, default to development
process.env.NODE_ENV = process.env.NODE_ENV || 'development';
var env = require('./config/env.js');

// Load, configure and initialize the Express module given its configuration
var express = require('./config/express');
var app = express();

app.use(oslcService(env));

app.use(function(err, req, res, next){
	console.error(err.stack);
	res.send(500, 'Something broke!');
});

app.listen(env.listenPort, env.listenHost);
module.exports = app;
console.log('oslc-server running on port:'+env.listenPort);