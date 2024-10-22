CREATE TABLE funcionario
(
    id              SERIAL PRIMARY KEY,
    email           VARCHAR(255) NOT NULL UNIQUE,
    nome            VARCHAR(255) NOT NULL,
    data_nascimento DATE         NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    user_id         INT          NOT NULL,                         -- Assuming you want to link it to the users table
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) -- Foreign key constraint
);
