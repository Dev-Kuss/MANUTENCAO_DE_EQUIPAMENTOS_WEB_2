Aqui está a versão final do **README.md**, agora incluindo as informações dos requisitos funcionais e não funcionais com base no arquivo **Manutenção de Equipamentos - 2024-2.pdf**:

---

# Projeto de Manutenção de Equipamentos

## Descrição
Este é um projeto de controle de manutenção de equipamentos, desenvolvido como parte da disciplina **Desenvolvimento Web II**. O sistema gerencia solicitações de manutenção, histórico de serviços, e oferece funcionalidades tanto para clientes quanto para funcionários.

## Tecnologias Utilizadas

- **Java 21 LTS** - Linguagem de programação principal do backend
- **Spring Boot 3.3.4** - Framework para simplificação do desenvolvimento Java
- **Spring Data JPA** - Para integração com banco de dados
- **PostgreSQL** - Banco de dados utilizado
- **Flyway** - Para controle de versões do banco de dados
- **Docker Compose** - Para orquestração de containers
- **Lombok** - Redução de boilerplate no código Java
- **Springdoc OpenAPI** - Para documentação automática da API

## Requisitos Funcionais

### Perfil Cliente
- **RF001 - Autocadastro:** O cliente pode se cadastrar no sistema com CPF, nome, e-mail, endereço completo e telefone.
- **RF002 - Login:** O cliente realiza login usando e-mail e senha.
- **RF003 - Página Inicial:** Visualização das solicitações de manutenção, com detalhes e histórico.
- **RF004 - Solicitação de Manutenção:** Criação de solicitação com detalhes do equipamento e defeito.
- **RF005 - Aprovação/Rejeição de Orçamento:** O cliente pode aprovar ou rejeitar o orçamento.
- **RF010 - Pagamento de Serviço:** O cliente paga o serviço após a manutenção.

### Perfil Funcionário
- **RF011 - Página Inicial:** Exibe todas as solicitações em estado "ABERTA" para que o funcionário possa efetuar o orçamento.
- **RF012 - Efetuar Orçamento:** Funcionalidade para gerar o orçamento para a solicitação de manutenção.
- **RF013 - Visualização de Solicitações:** Exibe e filtra solicitações por estado e data.
- **RF014 - Efetuar Manutenção:** O funcionário realiza a manutenção do equipamento ou redireciona para outro funcionário.
- **RF016 - Finalizar Solicitação:** Finaliza a solicitação após o serviço.
- **RF017 - CRUD de Categoria de Equipamento:** Funcionalidade para gerenciar as categorias dos equipamentos.
- **RF018 - CRUD de Funcionários:** Funcionalidade para gerenciar os funcionários do sistema.

## Requisitos Não-Funcionais

- O sistema será implementado utilizando Angular v17+ no front-end.
- As senhas dos usuários serão criptografadas utilizando SHA-256 com SALT.
- Todas as tabelas do banco de dados devem estar normalizadas (3FN), com exceção das tabelas de endereço.
- Consultas ao banco de dados devem utilizar boas práticas, incluindo JOINS, quando necessário.
- O layout deve ser responsivo e desenvolvido utilizando um framework como Bootstrap, Material ou Tailwind.
- O sistema será testado utilizando o navegador Firefox na sua versão mais recente.

## Configuração do Projeto

### Banco de Dados
O projeto está configurado para utilizar o **PostgreSQL**. As configurações de acesso ao banco de dados estão localizadas no arquivo `application.properties`, e a configuração do container do banco está no `docker-compose.yaml`.

### Docker
O arquivo `docker-compose.yaml` contém a configuração dos containers necessários para a execução da aplicação, incluindo o PostgreSQL.

## Execução do Projeto com Docker Compose

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/projeto-manutencao-equipamentos.git
   ```

2. Navegue até a pasta do projeto:
   ```bash
   cd projeto-manutencao-equipamentos
   ```

3. Execute o projeto usando Docker Compose:
   ```bash
   docker-compose up -d
   ```

4. Acesse o Swagger UI para explorar a API:
   ```
   http://localhost:8080/swagger-ui.html
   ```

Isso irá iniciar tanto a aplicação quanto o banco de dados PostgreSQL.

## Estrutura da Aplicação

### Funcionalidades do Cliente
- Autocadastro de clientes
- Solicitação de manutenção
- Visualização de histórico de solicitações
- Aprovação e rejeição de orçamentos

### Funcionalidades do Funcionário
- CRUD de funcionários
- Visualização de solicitações pendentes
- Geração de orçamento
- Finalização de manutenções

### Entidades Principais

- **Cliente**: Representa os clientes que fazem solicitações de manutenção.
- **Funcionario**: Gerencia as solicitações e realiza orçamentos e manutenções.
- **Solicitação**: Registro de um pedido de manutenção, incluindo histórico de alterações de status.

## Contribuição

Para contribuir com o projeto, siga os passos abaixo:

1. Crie um fork do repositório
2. Crie uma nova branch com sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit de suas mudanças:
   ```bash
   git commit -m 'Adiciona minha feature'
   ```
4. Envie sua branch:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request

## Licença
Este projeto está licenciado sob os termos da licença MIT.

---

