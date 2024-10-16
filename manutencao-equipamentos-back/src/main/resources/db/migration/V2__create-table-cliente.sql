CREATE TABLE cliente
(
    id          BIGSERIAL NOT NULL,
    nome        VARCHAR(255) NOT NULL,
    cpf         VARCHAR(14)  NOT NULL,
    email       VARCHAR(255) NOT NULL,
    telefone    VARCHAR(20),
    senha_hash  VARCHAR(255) NOT NULL,
    salt        VARCHAR(255) NOT NULL,
    endereco_id BIGINT,
    PRIMARY KEY (id)
);
