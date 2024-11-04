-- Histórico completo de transições para cada solicitação
INSERT INTO historico_solicitacao (id_solicitacao, data_hora, descricao, id_funcionario, id_cliente)
VALUES
    -- Histórico para Notebook Acer
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Notebook Acer'), 
     NOW() - INTERVAL '20 days', 
     'Solicitação criada',
     NULL,
     (SELECT id FROM cliente WHERE nome = 'João')),
     
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Notebook Acer'), 
     NOW() - INTERVAL '19 days', 
     'Solicitação analisada e orçamento realizado no valor de R$ 500,00',
     '97436adf-c107-4939-9cef-31d11c9a96dc',
     NULL),
     
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Notebook Acer'), 
     NOW() - INTERVAL '17 days', 
     'Orçamento aprovado pelo cliente',
     NULL,
     (SELECT id FROM cliente WHERE nome = 'João')),

    -- Histórico para Teclado Microsoft
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Teclado Microsoft'), 
     NOW() - INTERVAL '15 days', 
     'Solicitação criada',
     NULL,
     (SELECT id FROM cliente WHERE nome = 'João')),
     
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Teclado Microsoft'), 
     NOW() - INTERVAL '14 days', 
     'Solicitação analisada e orçamento realizado no valor de R$ 600,00',
     '71adaa53-990a-486c-ad1c-265eae6f3a7f',
     NULL),
     
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Teclado Microsoft'), 
     NOW() - INTERVAL '12 days', 
     'Orçamento aprovado pelo cliente',
     NULL,
     (SELECT id FROM cliente WHERE nome = 'João')),

    -- Histórico para Desktop Dell
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Desktop Dell'), 
     NOW() - INTERVAL '19 days', 
     'Solicitação criada',
     NULL,
     (SELECT id FROM cliente WHERE nome = 'José')),
     
    ((SELECT id_solicitacao FROM solicitacao WHERE descricao_equipamento = 'Desktop Dell'), 
     NOW() - INTERVAL '18 days', 
     'Solicitação analisada e orçamento realizado no valor de R$ 700,00',
     '97436adf-c107-4939-9cef-31d11c9a96dc',
     NULL);