CREATE TABLE pagamento (
                           id BIGSERIAL PRIMARY KEY NOT NULL,
                           id_solicitacao BIGINT NOT NULL,
                           valor NUMERIC(38, 2) NOT NULL,
                           data_hora_pagamento TIMESTAMP NOT NULL,
                           FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id_solicitacao)
);
