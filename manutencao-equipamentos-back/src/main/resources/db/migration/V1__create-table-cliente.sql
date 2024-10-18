CREATE TABLE cliente
(
    id          BIGSERIAL NOT NULL,
    nome        VARCHAR(255),
    cpf         VARCHAR(255) UNIQUE,
    email       VARCHAR(255),
    telefone    VARCHAR(255),
    senha_hash  VARCHAR(255),
    salt        VARCHAR(255),
    cep         VARCHAR(255),
    logradouro  VARCHAR(255),
    numero      VARCHAR(255),
    complemento VARCHAR(255),
    bairro      VARCHAR(255),
    cidade      VARCHAR(255),
    estado      VARCHAR(255),
    CONSTRAINT pk_cliente PRIMARY KEY (id)
);
