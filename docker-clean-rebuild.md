# Docker Build Commands

## Limpar recursos do Docker e Construir e iniciar os containers

```bash
docker system prune -a --volumes -f
docker-compose up --build
```

## Compilar e rodar/reiniciar o backend

```bash
docker compose up -d --build backend
```

## Compilar e rodar/reiniciar o frontend

```bash
docker compose up -d --build frontend
```