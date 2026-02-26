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
 * oslc-server: An OSLC 3.0 server reference implementation that uses
 * oslc-service Express middleware. Initializes the server, connects
 * to Fuseki via ldp-service-jena, and serves OSLC resources.
 */

import express, { type Request, type Response, type NextFunction } from 'express';
import { fileURLToPath } from 'node:url';
import { dirname, join } from 'node:path';
import { oslcService } from 'oslc-service';
import { JenaStorageService } from 'ldp-service-jena';
import { env } from './env.js';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

console.log('configuration:');
console.dir(env);

const app = express();

// Serve static files
app.use(express.static(join(__dirname, '..', 'public')));
app.use('/dialog', express.static(join(__dirname, '..', 'dialog')));

// Initialize storage and mount OSLC service
const storage = new JenaStorageService();

try {
  await storage.init(env);
  app.use(await oslcService(env, storage));
} catch (err) {
  console.error(err);
  console.error("Can't initialize the Jena storage service.");
}

// Error handling
app.use((err: Error, _req: Request, res: Response, _next: NextFunction) => {
  console.error(err.stack);
  res.status(500).send('Something broke!');
});

app.listen(env.listenPort, env.listenHost, () => {
  console.log('oslc-server running on ' + env.appBase);
});
