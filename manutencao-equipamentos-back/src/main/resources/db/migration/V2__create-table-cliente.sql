CREATE TABLE cliente (
                         id BIGSERIAL PRIMARY KEY NOT NULL,
                         cpf VARCHAR(11) NOT NULL UNIQUE,
                         nome VARCHAR(255) NOT NULL,
                         telefone VARCHAR(20) NOT NULL,
                         cep VARCHAR(20) NOT NULL,
                         logradouro VARCHAR(255) NOT NULL,
                         numero VARCHAR(10) NOT NULL,
                         complemento VARCHAR(255),
                         bairro VARCHAR(255) NOT NULL,
                         cidade VARCHAR(255) NOT NULL,
                         estado VARCHAR(50) NOT NULL,
                         FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);
