import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: '../public',
    emptyOutDir: true,
  },
  server: {
    proxy: {
      '/oslc': 'http://localhost:3001',
      '/resource': 'http://localhost:3001',
      '/compact': 'http://localhost:3001',
      '/sparql': 'http://localhost:3001',
      '/dialog': 'http://localhost:3001',
    },
  },
});
