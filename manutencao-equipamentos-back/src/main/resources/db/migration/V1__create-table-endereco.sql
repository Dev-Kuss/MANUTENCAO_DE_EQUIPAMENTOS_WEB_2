CREATE TABLE endereco
(
    id          SERIAL PRIMARY KEY,
    cep         VARCHAR(8),
    rua         VARCHAR(255),
    numero      VARCHAR(10),
    complemento VARCHAR(255),
    bairro      VARCHAR(255),
    cidade      VARCHAR(255),
    estado      VARCHAR(2)
);