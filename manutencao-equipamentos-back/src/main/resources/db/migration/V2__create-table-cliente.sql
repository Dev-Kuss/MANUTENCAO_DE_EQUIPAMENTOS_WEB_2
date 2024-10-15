CREATE TABLE cliente
(
    id          SERIAL PRIMARY KEY,
    cpf         VARCHAR(11)  NOT NULL UNIQUE,
    nome        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    telefone    VARCHAR(20),
    endereco_id INT,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);