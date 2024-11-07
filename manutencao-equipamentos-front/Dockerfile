# Use a Node.js version that's compatible with Angular CLI
FROM node:18.20.0-alpine

# Set working directory to the Angular project root
WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies (including dev dependencies)
RUN npm install

# Install Angular CLI globally to avoid workspace issues
RUN npm install -g @angular/cli

# Expose the port Angular uses
EXPOSE 8081
EXPOSE 9229

# Set the default command to serve the Angular app
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200", "--poll=2000"]

