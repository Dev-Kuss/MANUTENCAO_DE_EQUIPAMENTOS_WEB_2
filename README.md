# Aplicação Web de Manutenção de Equipamentos

## Visão Geral
Este é um projeto desenvolvido em **Spring Boot** como parte da disciplina de **Desenvolvimento Web II** no curso de **Tecnologia em Análise e Desenvolvimento de Sistemas** da **UFPR**. O sistema implementa um **Controle de Manutenção de Equipamentos**, onde clientes podem registrar solicitações de serviço e acompanhar o histórico de mudanças de status. Funcionários podem gerenciar essas solicitações, realizar orçamentos, efetuar manutenções e gerar relatórios.

### Funcionalidades Principais
- Perfis de Cliente e Funcionário com login seguro.
- Registro de solicitações de manutenção de equipamentos com rastreamento de status.
- Funcionalidades específicas para clientes e funcionários:
  - **Clientes:** Autocadastro, visualização de solicitações, aprovação ou rejeição de serviços, pagamento de serviços.
  - **Funcionários:** Visualização e gestão de solicitações, realização de orçamentos, manutenção de categorias de equipamentos e funcionários, geração de relatórios de receita.

### Requisitos do Sistema
- **Backend:** Spring Boot (v3.3.4) com **Java 23**.
- **Banco de Dados:** PostgreSQL.
- **Front-end:** Angular 17+ (implementação standalone) para comunicação com o backend via API REST.
- **Outras Tecnologias:** Bootstrap ou Material para layout, jQuery para manipulação dinâmica de telas, API ViaCEP para preenchimento automático de endereços.

### Requisitos Funcionais
- **RF001:** Autocadastro de clientes com CPF e e-mail únicos, integração com API ViaCEP para preenchimento de endereços.
- **RF002:** Login seguro com identificação automática do perfil.
- **RF003:** Página inicial do cliente exibindo as solicitações em ordem cronológica.
- **RF004:** Registro de novas solicitações de manutenção.
- **RF005-RF010:** Aprovação, rejeição, resgate de serviços e pagamento de manutenções.
- **RF011-RF016:** Funcionalidades de funcionários como efetuação de orçamentos, manutenção e finalização de solicitações.
- **RF017-RF020:** CRUD de categorias e funcionários, geração de relatórios de receita.

### Configuração do Projeto

1. **Docker:** O projeto inclui um Dockerfile configurado para criar uma imagem do aplicativo Spring Boot e executar o sistema em um container. Para rodar o projeto, basta seguir os passos:
   ```bash
   docker build -t manutencao-equipamentos .
   docker run -p 8080:8080 manutencao-equipamentos
   ```
   
2. **Banco de Dados:** O projeto utiliza o PostgreSQL. Configure o banco de dados com as seguintes variáveis de ambiente no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
   spring.datasource.username=user
   spring.datasource.password=password
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```

### Dependências
As principais dependências do projeto estão listadas no arquivo `pom.xml` e incluem:
- **Spring Boot Starter Web**: Para construir APIs REST.
- **Spring Boot Starter Data JPA**: Para integração com o banco de dados.
- **Spring Boot Starter Actuator**: Monitoramento do sistema.
- **PostgreSQL**: Driver para conexão com o banco de dados.
- **Lombok**: Facilita a redução de código boilerplate (opcional).
- **Docker Compose**: Suporte para execução do ambiente via Docker.
- **Springdoc OpenAPI**: Para geração de documentação da API.

### Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd manutencao-equipamentos
   ```
3. Construa o projeto com Maven:
   ```bash
   ./mvnw clean install
   ```
4. Execute o aplicativo:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acesse o sistema em: `http://localhost:8080`.

### Estrutura do Projeto

- `src/main/java`: Contém o código-fonte da aplicação.
  - `controller`: Controladores REST.
  - `model`: Entidades JPA.
  - `repository`: Interfaces para acesso ao banco de dados.
  - `service`: Lógica de negócios.
  
- `src/main/resources`: Arquivos de configuração e templates.
  - `application.properties`: Configurações da aplicação.
  - `static`: Arquivos estáticos (CSS, JS).
  
### Contribuições

Sinta-se à vontade para enviar pull requests ou abrir issues para sugerir melhorias.

### Licença
Este projeto é parte de um trabalho acadêmico e não possui uma licença formal. Para mais informações, entre em contato com o autor.
