CREATE TABLE historico_solicitacao (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    id_solicitacao BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    id_funcionario UUID,
    id_cliente UUID,
    FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id_solicitacao),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);
