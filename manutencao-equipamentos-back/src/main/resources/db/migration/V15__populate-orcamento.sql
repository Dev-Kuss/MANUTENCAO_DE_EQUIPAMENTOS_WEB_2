INSERT INTO orcamento (id_solicitacao, valor, data_hora, id_funcionario)
VALUES
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Teclado Microsoft'), 600.00, NOW() - INTERVAL '15 days', '97436adf-c107-4939-9cef-31d11c9a96dc'),
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Desktop Dell'), 700.00, NOW() - INTERVAL '19 days', '71adaa53-990a-486c-ad1c-265eae6f3a7f'),
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Mouse Microsoft'), 800.00, NOW() - INTERVAL '9 days', '382aad32-bd4e-4b2d-bffe-d10379e3e1c2'),
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Notebook Dell'), 900.00, NOW() - INTERVAL '8 days', '97436adf-c107-4939-9cef-31d11c9a96dc'),
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Impressora Canon'), 1000.00, NOW() - INTERVAL '4 days', '71adaa53-990a-486c-ad1c-265eae6f3a7f');
