CREATE TABLE historico_solicitacao (
                                       id BIGSERIAL PRIMARY KEY NOT NULL,
                                       id_solicitacao BIGINT NOT NULL,
                                       data_hora TIMESTAMP NOT NULL,
                                       estado_anterior VARCHAR(50),
                                       estado_atual VARCHAR(50) NOT NULL,
                                       id_funcionario BIGINT NOT NULL,
                                       FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id_solicitacao),
                                       FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);
