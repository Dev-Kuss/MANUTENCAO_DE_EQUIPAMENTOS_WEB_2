FROM node:20-alpine

WORKDIR /app

# Instalar dependências como root
COPY package*.json ./
RUN npm install
RUN npm install -g @angular/cli@17

# Copiar arquivos do projeto
COPY . .

# Criar diretório .angular e definir permissões
RUN mkdir -p /app/.angular/cache && \
    chmod -R 777 /app/.angular && \
    chmod -R 777 /app/node_modules

# Manter como root para execução (temporariamente)
USER root

# Expose ports
EXPOSE 8081
EXPOSE 9229

# We'll let docker-compose handle the command
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "8081", "--poll=2000", "--disable-host-check"]

