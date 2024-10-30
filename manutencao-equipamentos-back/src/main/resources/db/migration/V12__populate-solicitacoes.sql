INSERT INTO solicitacao (data_hora, descricao_equipamento, estado, id_categoria, id_cliente)
VALUES
    (NOW() - INTERVAL '20 days', 'Notebook Acer', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '15 days', 'Teclado Microsoft', 'Em andamento',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '12 days', 'Desktop HP', 'Finalizado',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '10 days', 'Impressora Epson', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Impressora'),
     (SELECT id FROM cliente WHERE nome = 'João'));

-- Solicitações de José
INSERT INTO solicitacao (data_hora, descricao_equipamento, estado, id_categoria, id_cliente)
VALUES
    (NOW() - INTERVAL '19 days', 'Desktop Dell', 'Em andamento',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '14 days', 'Notebook Lenovo', 'Finalizado',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '9 days', 'Mouse Microsoft', 'Em andamento',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '7 days', 'Teclado Corsair', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'José'));

-- Solicitações de Joana
INSERT INTO solicitacao (data_hora, descricao_equipamento, estado, id_categoria, id_cliente)
VALUES
    (NOW() - INTERVAL '18 days', 'Impressora HP', 'Finalizado',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Impressora'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '13 days', 'Mouse Logitech', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '8 days', 'Notebook Dell', 'Em andamento',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '5 days', 'Teclado Logitech', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'Joana'));

-- Solicitações de Joaquina
INSERT INTO solicitacao (data_hora, descricao_equipamento, estado, id_categoria, id_cliente)
VALUES
    (NOW() - INTERVAL '17 days', 'Mouse Logitech', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    (NOW() - INTERVAL '16 days', 'Notebook HP', 'Finalizado',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    (NOW() - INTERVAL '11 days', 'Teclado Razer', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    (NOW() - INTERVAL '6 days', 'Desktop Lenovo', 'Finalizado',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina'));

-- Solicitações adicionais
INSERT INTO solicitacao (data_hora, descricao_equipamento, estado, id_categoria, id_cliente)
VALUES
    (NOW() - INTERVAL '4 days', 'Impressora Canon', 'Em andamento',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Impressora'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '3 days', 'Desktop Asus', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '2 days', 'Notebook Samsung', 'Finalizado',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '1 day', 'Mouse Apple', 'Pendente',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina'));
