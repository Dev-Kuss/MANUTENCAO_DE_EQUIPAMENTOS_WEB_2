CREATE TABLE orcamento (
   id SERIAL PRIMARY KEY,
   id_solicitacao INT NOT NULL,
   valor DECIMAL(10, 2) NOT NULL,
   data_hora TIMESTAMP NOT NULL,
   id_funcionario INT NOT NULL,
   FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id),
   FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);