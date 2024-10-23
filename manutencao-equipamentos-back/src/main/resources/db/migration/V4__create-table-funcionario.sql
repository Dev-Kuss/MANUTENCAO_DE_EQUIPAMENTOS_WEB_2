CREATE TABLE funcionario (
                             id BIGSERIAL PRIMARY KEY NOT NULL,
                             nome VARCHAR(255) NOT NULL,
                             telefone VARCHAR(20) NOT NULL,
                             data_nascimento DATE,
                             FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);
