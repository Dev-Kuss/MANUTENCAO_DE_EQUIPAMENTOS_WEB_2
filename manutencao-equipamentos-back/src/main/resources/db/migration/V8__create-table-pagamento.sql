CREATE TABLE pagamento (
   id SERIAL PRIMARY KEY,
   id_solicitacao INT NOT NULL,
   valor DECIMAL(10, 2) NOT NULL,
   data_hora_pagamento TIMESTAMP NOT NULL,
   FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id)
);