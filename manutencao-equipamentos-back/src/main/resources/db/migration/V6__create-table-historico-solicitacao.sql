CREATE TABLE historico_solicitacao (
   id SERIAL PRIMARY KEY,
   id_solicitacao INT NOT NULL,
   data_hora TIMESTAMP NOT NULL,
   estado_anterior VARCHAR(50),
   estado_atual VARCHAR(50) NOT NULL,
   id_funcionario INT NOT NULL,
   FOREIGN KEY (id_solicitacao) REFERENCES solicitacao(id_solicitacao),
   FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);