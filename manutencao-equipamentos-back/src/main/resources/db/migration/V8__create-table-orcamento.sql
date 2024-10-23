CREATE TABLE orcamento (
                           id BIGSERIAL PRIMARY KEY NOT NULL,
                           id_solicitacao BIGINT NOT NULL,
                           valor NUMERIC(38, 2) NOT NULL,
                           data_hora TIMESTAMP NOT NULL,
                           id_funcionario UUID NOT NULL,
                           FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id_solicitacao),
                           FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);
