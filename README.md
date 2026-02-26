# oslc-server

[![Discourse status](https://img.shields.io/discourse/https/meta.discourse.org/status.svg)](https://forum.open-services.net/)
[![Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/OSLC/chat)

An [OSLC 3.0](https://docs.oasis-open-projects.org/oslc-op/core/v3.0/oslc-core.html) server reference implementation built with Node.js and Express. It uses the **oslc-service** Express middleware for OSLC operations, backed by **Apache Jena Fuseki** for RDF persistence.

oslc-server only provides OSLC and LDP REST services. It does not provide any additional application capabilities or UI. The REST services are intended to be used programmatically, possibly to support an OSLC3 test server and reference implementation, or by the oslc-browser sample application.

Many thanks to Steve Speicher and Sam Padgett for their valuable contribution to LDP and the LDP middleware upon which this service is built.

## Architecture

oslc-server is built from several modules in the oslc4js workspace:

- **oslc-server** -- Express application entry point and static assets
- **oslc-service** -- Express middleware providing OSLC 3.0 services, built on ldp-service
- **ldp-service** -- Express middleware implementing the W3C LDP protocol (GET, HEAD, PUT, POST, DELETE for RDF resources and containers)
- **ldp-service-jena** -- Storage backend that persists RDF graphs in Apache Jena Fuseki
- **storage-service** -- Abstract storage interface shared by all backends

The Express app (`src/app.ts`) serves static files, then mounts oslc-service which delegates to ldp-service for all LDP operations. Configuration is read from `config.json` with environment variable overrides.

## Running

### Prerequisites

- [Node.js](http://nodejs.org) v22 or later
- [Apache Jena Fuseki](https://jena.apache.org/documentation/fuseki2/) running with a dataset configured

### Setup

Install dependencies from the workspace root:

    $ npm install

Build the TypeScript source:

    $ cd oslc-server
    $ npm run build

### Configuration

Edit `config.json` to match your environment:

```json
{
  "scheme": "http",
  "host": "localhost",
  "port": 3001,
  "context": "/",
  "jenaURL": "http://localhost:3030/univ/"
}
```

- **port** -- The port to listen on (3001 by default, to avoid conflict with ldp-app on 3000)
- **context** -- The URL path prefix for OSLC/LDP resources
- **jenaURL** -- The Fuseki dataset endpoint URL

### Start

Start Fuseki with your dataset, then:

    $ npm start

The server starts on the configured port. Use a REST client or the ldp-app visualization to interact with resources.

## REST API

oslc-server exposes OSLC 3.0 discovery services and W3C LDP resource management. All RDF endpoints accept and produce `text/turtle`, `application/ld+json`, and `application/rdf+xml`.

### OSLC Discovery

The ServiceProviderCatalog is an LDP BasicContainer at `{context}/oslc` (e.g. `http://localhost:3000/oslc`), initialized at startup from `config/catalog-template.ttl`.

- **GET /oslc** -- Returns the catalog with `ldp:contains` links to ServiceProviders
- **POST /oslc** -- Creates a new ServiceProvider. Body is Turtle with at least `dcterms:title`. Use `Slug` header to suggest a URI segment. Returns `201` with `Location`
- **GET /oslc/{sp}** -- Returns a ServiceProvider with its services, creation factories, and dialogs
- **DELETE /oslc/{sp}** -- Deletes a ServiceProvider and removes it from the catalog

Example -- create a ServiceProvider:

    POST /oslc HTTP/1.1
    Content-Type: text/turtle
    Slug: myproject

    @prefix dcterms: <http://purl.org/dc/terms/> .
    <> dcterms:title "My Project" .

### OSLC Delegated UI

- **GET /dialog/create?shape={shapeURI}&creation={creationURI}** -- Returns an HTML creation form generated from the ResourceShape. On submit, POSTs Turtle to the creation URI and responds via the OSLC delegated UI protocol (`postMessage` or `windowName`)
- **GET /compact?uri={resourceURI}** -- Resource preview. `Accept: text/html` returns a small preview HTML fragment; `Accept: text/turtle` returns an `oslc:Compact` RDF description with a `smallPreview` link

### LDP Resources and Containers

All resources are managed under the configured `context` path. LDP supports RDF Source resources and containers (BasicContainer, DirectContainer).

- **GET /{path}** -- Returns the resource or container. Supports `If-None-Match`/`ETag` conditional requests. Containers support `Prefer` header to control containment and membership triples
- **HEAD /{path}** -- Same as GET without the body. Returns `ETag`, `Content-Type`, `Link`, and `Allow` headers
- **POST /{container}** -- Creates a new resource in the container. Use `Slug` header to suggest a URI. Returns `201` with `Location`. For DirectContainers, automatically adds membership triples
- **PUT /{resource}** -- Updates an existing RDF Source (not containers). Requires `If-Match` with the current `ETag`. Can also create a resource at a specific URI
- **DELETE /{path}** -- Deletes the resource and cleans up container membership (`ldp:contains` for BasicContainers, `hasMemberRelation` for DirectContainers)
- **OPTIONS /{path}** -- Returns `Allow` and `Link` headers describing supported methods and interaction model

## Static Assets

- `public/` -- Favicon, stylesheets, usage documentation
- `dialog/` -- OSLC delegated UI dialogs (creation and selection)
- `example/` -- Sample Turtle data files

## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
