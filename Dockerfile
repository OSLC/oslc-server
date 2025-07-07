# Use the Node 12 base image
FROM node:12

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package*.json ./oslc-server

# Install the dependencies
RUN npm install

# Copy the rest of the application files
COPY . ./oslc-server
COPY ../oslc-service ./oslc-service

# Expose port 3000
EXPOSE 3000

WORKDIR /app/oslc-server
# Run the application with npm start
CMD ["npm", "start"]