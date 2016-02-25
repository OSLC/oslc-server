exports.render = function(req, res) {
	// render the index template with the given template varaibles
	
	// renders from the views as configured in the Express views environment variable
	// renders index.ejs because that's the ejs 'view engine' rendering engine expects
	// These two lines of code in express.js configure the views folder and engine 
	//	 app.set('views', './app/views');
	//   app.set('view engine', 'ejs');
	// Express uses. res.render therefore resolves 'index' to './app/views/index.ejs'

	// Configure the views folder and template engine used to render dynamic HTML
	res.render('index', {
		title: 'OSLC Server',
	});
};
