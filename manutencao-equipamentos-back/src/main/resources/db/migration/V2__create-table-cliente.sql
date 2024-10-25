CREATE TABLE cliente (
                         id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
                         cpf VARCHAR(255) NOT NULL,
                         nome VARCHAR(255) NOT NULL,
                         telefone VARCHAR(255),
                         cep VARCHAR(255),
                         logradouro VARCHAR(255),
                         numero VARCHAR(255),
                         complemento VARCHAR(255),
                         bairro VARCHAR(255),
                         cidade VARCHAR(255),
                         estado VARCHAR(255)
);
