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
 * context, etc.), falling back to what's in config.json.
 */

import { fileURLToPath } from 'node:url';
import { dirname, join } from 'node:path';
import { readFileSync } from 'node:fs';
import { format as formatURL } from 'node:url';
import type { StorageEnv } from 'storage-service';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

interface Config {
  scheme: string;
  host: string;
  port: number;
  context: string;
  jenaURL: string;
}

const config: Config = JSON.parse(
  readFileSync(join(__dirname, '..', 'config.json'), 'utf-8')
);

function addSlash(url: string): string {
  return url.endsWith('/') ? url : url + '/';
}

interface URLFormatOptions {
  protocol?: string;
  hostname?: string;
  host?: string;
  port?: number;
  pathname?: string;
}

function toURL(urlObj: URLFormatOptions): string {
  const opts = { ...urlObj };
  if ((opts.protocol === 'http' && opts.port === 80) ||
      (opts.protocol === 'https' && opts.port === 443)) {
    delete opts.port;
  }
  return formatURL(opts as Parameters<typeof formatURL>[0]);
}

export interface AppEnv extends StorageEnv {
  listenHost: string;
  listenPort: number;
  scheme: string;
  host: string;
  port?: number;
  context: string;
  ldpBase: string;
  templatePath?: string;
}

const listenHost = process.env.VCAP_APP_HOST || process.env.OPENSHIFT_NODEJS_IP || config.host;
const listenPort = Number(process.env.VCAP_APP_PORT || process.env.OPENSHIFT_NODEJS_PORT || config.port);

let scheme: string;
let host: string;
let port: number | undefined;
let context: string;
let appBase: string;
let ldpBase: string;

if (process.env.LDP_BASE) {
  ldpBase = addSlash(process.env.LDP_BASE);
  const parsed = new URL(ldpBase);
  scheme = parsed.protocol.replace(':', '');
  host = parsed.hostname;
  port = parsed.port ? Number(parsed.port) : undefined;
  context = parsed.pathname;
  appBase = toURL({ protocol: scheme, hostname: host, port });
} else {
  const appInfo = JSON.parse(process.env.VCAP_APPLICATION || '{}') as { application_uris?: string[] };
  scheme = process.env.VCAP_APP_PORT ? 'http' : config.scheme;

  if (appInfo.application_uris) {
    host = appInfo.application_uris[0];
  } else {
    host = process.env.HOSTNAME || config.host;
  }

  if (!process.env.VCAP_APP_PORT) {
    port = config.port;
  }
  context = addSlash(config.context);

  appBase = toURL({ protocol: scheme, hostname: host, port });
  ldpBase = toURL({ protocol: scheme, hostname: host, port, pathname: context });
}

export const env: AppEnv = {
  listenHost,
  listenPort,
  scheme,
  host,
  port,
  context,
  appBase,
  ldpBase,
  jenaURL: config.jenaURL,
  templatePath: join(__dirname, '..', 'config', 'catalog-template.ttl'),
};
