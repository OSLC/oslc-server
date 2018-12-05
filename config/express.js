var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var oslcService = require('oslc-service');
var ldpService = require('ldp-service');

var env = require('./env.js');

module.exports = function() {
	var app = express();

	// configure the middleware that will execute on all requests (in order)
	if (process.env.NODE_ENV === 'development') {
		app.use(logger('dev')); // log request during development
	} else if (process.env.NODE_ENV === 'production') {
		app.use(compress());   // compress responses in production
	}
	app.use(bodyParser.urlencoded({
		extended: true
	}));
	app.use(bodyParser.json());

	// Configure the views folder and template engine used to render dynamic HTML
	app.set('views', './app/views');
	app.set('view engine', 'ejs');

	// uncomment after placing your favicon in /public
	app.use(favicon('./public/images/favicon.ico'));
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded({ extended: false }));
	app.use(cookieParser());

	// set the oslcroot to all OSLC resources
	// initialize database and set up LDP services and viz when ready
	env.storageService = require('ldp-service-jena');
	env.storageService.init(env, function(err) {
		if (err) {
			// don't add the services that depend on the database if it can't be initialized
			console.error(err);
			console.error(`Can't initialize the ${env.storageImpl} data service.`);
		} else {
			var oslcService = require('oslc-service');
			app.use(oslcService(env));  // env specifies what is routed to oslc-service
			var ldpService = require('ldp-service').ldpService;
			app.use(ldpService(env));
			// add the visualization middleware to support graph visualization,
		}
	});

	// set the default routes
	// The default index route
	require('../app/routes/index.server.routes.js')(app);

	app.use(express.static('./public'));

	// catch 404 and forward to error handler
	app.use(function(req, res, next) {
	  var err = new Error('Not Found');
	  err.status = 404;
	  next(err);
	});

	// error handlers

	// development error handler
	// will print stacktrace
	if (app.get('env') === 'development') {
	  app.use(function(err, req, res, next) {
	    res.status(err.status || 500);
	    res.render('error', {
	      message: err.message,
	      error: err
	    });
	  });
	}

	// production error handler
	// no stacktraces leaked to user
	app.use(function(err, req, res, next) {
	  res.status(err.status || 500);
	  res.render('error', {
	    message: err.message,
	    error: {}
	  });
	});
	return app;
}
