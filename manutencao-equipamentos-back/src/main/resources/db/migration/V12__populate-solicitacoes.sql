-- População da tabela solicitacao com os novos estados ajustados
INSERT INTO solicitacao (data_hora, descricao_equipamento, estado, id_categoria, id_cliente)
VALUES
    (NOW() - INTERVAL '20 days', 'Notebook Acer', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '15 days', 'Teclado Microsoft', 'ORÇADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '12 days', 'Desktop HP', 'FINALIZADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '10 days', 'Impressora Epson', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Impressora'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    -- Solicitações de José
    (NOW() - INTERVAL '19 days', 'Desktop Dell', 'ORÇADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '14 days', 'Notebook Lenovo', 'FINALIZADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '9 days', 'Mouse Microsoft', 'ORÇADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '7 days', 'Teclado Corsair', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    -- Solicitações de Joana
    (NOW() - INTERVAL '18 days', 'Impressora HP', 'FINALIZADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Impressora'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '13 days', 'Mouse Logitech', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '8 days', 'Notebook Dell', 'ORÇADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '5 days', 'Teclado Logitech', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    -- Solicitações de Joaquina
    (NOW() - INTERVAL '17 days', 'Mouse Logitech', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    (NOW() - INTERVAL '16 days', 'Notebook HP', 'FINALIZADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    (NOW() - INTERVAL '11 days', 'Teclado Razer', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Teclado'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    (NOW() - INTERVAL '6 days', 'Desktop Lenovo', 'FINALIZADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina')),

    -- Solicitações adicionais
    (NOW() - INTERVAL '4 days', 'Impressora Canon', 'ORÇADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Impressora'),
     (SELECT id FROM cliente WHERE nome = 'João')),

    (NOW() - INTERVAL '3 days', 'Desktop Asus', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Desktop'),
     (SELECT id FROM cliente WHERE nome = 'José')),

    (NOW() - INTERVAL '2 days', 'Notebook Samsung', 'FINALIZADA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Notebook'),
     (SELECT id FROM cliente WHERE nome = 'Joana')),

    (NOW() - INTERVAL '1 day', 'Mouse Apple', 'ABERTA',
     (SELECT id FROM categoria_equipamento WHERE nome_categoria = 'Mouse'),
     (SELECT id FROM cliente WHERE nome = 'Joaquina'));
