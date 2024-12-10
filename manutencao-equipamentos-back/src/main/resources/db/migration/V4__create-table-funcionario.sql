CREATE TABLE funcionario (
                             id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
                             nome VARCHAR(255) NOT NULL,
                             telefone VARCHAR(255),
                             data_nascimento DATE NOT NULL
);
