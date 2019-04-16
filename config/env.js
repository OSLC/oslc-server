/*
 * Copyright 2014 IBM Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Looks at environment variables for app configuration (base URI, port, LDP
 * context, etc.), falling back to what's in confg.json.
 */

var url = require("url");
var config = require('./config.js');


// Add a trailing / at the end of a URL path if needed
function addSlash(url) {
	if (url.substr(-1) == '/') {
		return url;
	} else {
		return url + '/';
	}
}

// Don't include default ports in URLs
function toURL(urlObj) {
	if ((urlObj.scheme === 'http' && urlObj.port === 80) ||
			(urlObj.scheme === 'https' && urlObj.port === 443)) {
		delete urlObj.port;
	}

	return url.format(urlObj);
}

// scheme, host, port, and base URI
exports.scheme = config.scheme;
exports.host = config.host;
exports.port = config.port;
exports.context = addSlash(config.context);

exports.appBase = toURL({
		protocol: exports.scheme,
		hostname: exports.host,
		port: exports.port
	});

// The ServiceProviderCatalog file used to initialize the database
exports.services = config.services;
exports.contentType = config.contentType;

// ldp-service-jena storage service 
exports.storageImpl = config.storageImpl;
exports.jenaURL = config.jenaURL;  // this is storage implementation specific



