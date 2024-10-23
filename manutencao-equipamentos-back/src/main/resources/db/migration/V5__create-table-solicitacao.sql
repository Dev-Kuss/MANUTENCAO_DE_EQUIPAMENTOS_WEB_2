CREATE TABLE solicitacao (
                             id_solicitacao BIGSERIAL PRIMARY KEY NOT NULL,
                             data_hora TIMESTAMP NOT NULL,
                             descricao_equipamento VARCHAR(30) NOT NULL,
                             estado VARCHAR(50) NOT NULL,
                             id_categoria BIGINT NOT NULL,
                             id_cliente BIGINT NOT NULL,
                             FOREIGN KEY (id_categoria) REFERENCES categoria_equipamento(id),
                             FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);
