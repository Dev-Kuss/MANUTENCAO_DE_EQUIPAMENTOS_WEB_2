Para atualizar o `README.md` com base nos arquivos fornecidos, vou integrar as informações obtidas dos arquivos `pom.xml`, `package.json`, `docker-compose.yml`, e `Dockerfile`.

Aqui está uma sugestão para o conteúdo atualizado do `README.md`:

---

# Projeto de Manutenção de Equipamentos

Este projeto é parte da disciplina de **Desenvolvimento Web 2** e visa a implementação de um sistema para gerenciamento e manutenção de equipamentos. Ele é composto por uma aplicação **backend** desenvolvida com **Spring Boot** e uma aplicação **frontend** desenvolvida com **Angular**.

## Arquitetura

A arquitetura deste projeto utiliza um ambiente **Dockerizado** com o uso de `docker-compose` para orquestrar os serviços do frontend, backend, e banco de dados. O backend foi construído com **Spring Boot** e o frontend com **Angular**.

### Principais Tecnologias Utilizadas:

- **Backend**: 
  - Java 21
  - Spring Boot 3.3.4
  - PostgreSQL (como banco de dados)
  - Spring Data JPA
  - Flyway (para migrações de banco de dados)
  - Spring Security com JWT
  - OpenAPI (documentação de APIs com Swagger)
  
- **Frontend**: 
  - Angular 17
  - TailwindCSS
  - Font Awesome (para ícones)

- **DevOps**:
  - Docker e Docker Compose
  - Integração com PostgreSQL via containers Docker
  - Versionamento de API com OpenAPI

## Estrutura do Projeto

### Backend (Spring Boot)
O backend é responsável por fornecer as APIs REST para a interação com o banco de dados e outras funcionalidades do sistema. O projeto backend está configurado no arquivo `pom.xml` e inclui as seguintes dependências principais:

- **Spring Boot Starter Actuator**: Para monitoramento.
- **Spring Boot Starter Data JPA**: Para integração com o banco de dados.
- **Spring Boot Starter Security**: Para autenticação e autorização com tokens JWT.
- **Flyway**: Para controle de versões do banco de dados.
- **PostgreSQL**: Banco de dados utilizado.

### Frontend (Angular)
O frontend foi desenvolvido usando o framework **Angular** e está configurado para ser servido em um container Docker. Ele se conecta ao backend por meio de chamadas API para gerenciar as operações do sistema.

Principais pacotes usados no frontend, conforme o arquivo `package.json`:

- **Angular** (versão 17.x)
- **Angular Material**: Biblioteca de componentes de UI.
- **TailwindCSS**: Para estilização.
- **Karma**: Para testes unitários.
- **RxJS**: Para manipulação de streams assíncronas.

### Docker e Docker Compose
A aplicação utiliza **Docker Compose** para orquestrar os serviços do backend e frontend, além de um serviço PostgreSQL. O arquivo `docker-compose.yml` gerencia esses containers e permite que o projeto seja facilmente configurado e executado localmente ou em ambiente de produção.

### Comandos Principais

#### Backend

- **Compilar e rodar o backend**:
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```

#### Frontend

- **Servir o frontend localmente**:
  ```bash
  npm install
  npm start
  ```

#### Docker

- **Iniciar todos os serviços via Docker Compose**:
  ```bash
  docker-compose up --build
  ```

- **Parar os serviços**:
  ```bash
  docker-compose down
  ```

## Como Contribuir

1. Faça um fork do repositório.
2. Crie sua branch (`git checkout -b minha-feature`).
3. Faça suas mudanças e comente (`git commit -m 'Minha nova feature'`).
4. Envie suas mudanças para o repositório remoto (`git push origin minha-feature`).
5. Crie um Pull Request.

## Licença

Este projeto é parte de um curso acadêmico e não possui uma licença aberta para uso externo.

---

Essa estrutura de `README.md` oferece uma visão completa do projeto e inclui as tecnologias utilizadas, a estrutura do projeto e os comandos principais para desenvolvimento e execução. Se houver alguma outra especificação ou ajuste que você gostaria de incluir, posso auxiliar.
