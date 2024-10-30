CREATE TABLE categoria_equipamento (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome_categoria VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT true NOT NULL
);
