#!/bin/bash

# Limpar recursos do Docker (imagens, containers, volumes, etc.)
docker system prune -a --volumes -f

# Construir e iniciar os containers definidos no docker-compose.yml
docker-compose up --build
