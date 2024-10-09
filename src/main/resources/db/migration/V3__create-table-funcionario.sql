CREATE TABLE funcionario (
     id SERIAL PRIMARY KEY,
     email VARCHAR(255) NOT NULL UNIQUE,
     nome VARCHAR(255) NOT NULL,
     data_nascimento DATE NOT NULL,
     senha VARCHAR(255) NOT NULL
);
