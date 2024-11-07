FROM node:18-buster

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Expose ports
EXPOSE 8081
EXPOSE 9229

# We'll let docker-compose handle the command
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "8081", "--poll=2000", "--disable-host-check"]

