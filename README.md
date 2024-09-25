# Controle de Manutenção de Equipamentos

## Descrição do Projeto

Este projeto é um sistema para controle de manutenção de equipamentos, desenvolvido para a disciplina de Desenvolvimento Web II, no curso de Tecnologia em Análise e Desenvolvimento de Sistemas (TADS) da UFPR. O sistema permite o gerenciamento de solicitações de manutenção de equipamentos, abrangendo funcionalidades para clientes e funcionários.

## Funcionalidades

### Perfis de Usuário

- **Cliente**:
  - Autocadastro com validação de CPF e e-mail únicos (RF001).
  - Login no sistema (RF002).
  - Visualização e gerenciamento das suas solicitações de manutenção (RF003).
  - Registro de novas solicitações (RF004).
  - Aprovação ou rejeição de orçamentos (RF005, RF006, RF007).
  - Pagamento de serviços após conclusão (RF010).

- **Funcionário**:
  - Visualização de solicitações em aberto e efetuação de orçamentos (RF011, RF012).
  - Execução e redirecionamento de manutenção (RF014, RF015).
  - Finalização de solicitações (RF016).
  - Gerenciamento de categorias de equipamento (CRUD) (RF017).
  - Gerenciamento de funcionários (CRUD) (RF018).
  - Geração de relatórios de receitas e categorias em PDF (RF019, RF020).

### Requisitos Não-Funcionais

- Layout responsivo e elaborado.
- Uso de Angular (v17+) no front-end e Spring Boot com REST no back-end.
- Implementação de padrões de projeto, boas práticas de programação e arquitetura.
- Senhas criptografadas com SHA-256 + SALT.
- Banco de dados normalizado (3FN), com consultas otimizadas via JOINS.
- Validação de campos no front-end (Angular) e back-end (Spring).
- API de consulta de endereço ViaCEP para preenchimento automático do endereço do cliente.
- Implementação de máscaras para formatação de campos (datas, valores monetários).

## Tecnologias Utilizadas

- **Front-end**: Angular (v17+)
- **Back-end**: Spring Boot, REST
- **Banco de Dados**: PostgreSQL ou MySQL
- **Bibliotecas e Frameworks**: Bootstrap, jQuery, API ViaCEP
- **Outras Tecnologias**: SHA-256 para criptografia, Firestore para testes com banco de dados

## Como Executar o Projeto

### Pré-requisitos

1. Node.js (para execução do Angular)
2. Java 11+ (para execução do Spring Boot)
3. PostgreSQL ou MySQL (para o banco de dados)
4. Navegador Firefox (versão mais recente)
