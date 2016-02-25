/*
 * An app route that maps the / URI paths to the default 'index' controller function
 */
module.exports = function(app) {
	var index = require('../controllers/index.server.controller');
	app.get('/', index.render);  // route / to controler's render method as the callback for the GET method
};

