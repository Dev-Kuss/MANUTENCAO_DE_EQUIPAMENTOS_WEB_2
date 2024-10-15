CREATE TABLE solicitacao (
     id SERIAL PRIMARY KEY,
     data_hora TIMESTAMP NOT NULL,
     descricao_equipamento VARCHAR(30) NOT NULL,
     estado VARCHAR(50) NOT NULL,  -- Estados: aberta, orçada, aprovada, etc.
     id_categoria INT NOT NULL,
     id_cliente INT NOT NULL,
     FOREIGN KEY (id_categoria) REFERENCES categoria_equipamento(id),
     FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);