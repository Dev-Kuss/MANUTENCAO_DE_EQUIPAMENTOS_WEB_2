-- Histórico de Solicitações
INSERT INTO historico_solicitacao (id_solicitacao, data_hora, estado_anterior, estado_atual, id_funcionario)
VALUES
    -- Solicitação 1 (Impressora HP)
    (1, NOW() - INTERVAL '17 days', 'Pendente', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 2 (Mouse Logitech)
    (2, NOW() - INTERVAL '13 days', 'Pendente', 'Em andamento', 1), -- Funcionario Maria
    (2, NOW() - INTERVAL '10 days', 'Em andamento', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 3 (Notebook Dell)
    (3, NOW() - INTERVAL '6 days', 'Pendente', 'Em andamento', 1), -- Funcionario Maria

    -- Solicitação 4 (Teclado Logitech)
    (4, NOW() - INTERVAL '2 days', 'Pendente', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 5 (Impressora Canon)
    (5, NOW() - INTERVAL '18 days', 'Pendente', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 6 (Teclado Microsoft)
    (6, NOW() - INTERVAL '14 days', 'Pendente', 'Em andamento', 1), -- Funcionario Maria
    (6, NOW() - INTERVAL '11 days', 'Em andamento', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 7 (Mouse HP)
    (7, NOW() - INTERVAL '12 days', 'Pendente', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 8 (Notebook Lenovo)
    (8, NOW() - INTERVAL '20 days', 'Pendente', 'Em andamento', 2), -- Funcionario Mário
    (8, NOW() - INTERVAL '18 days', 'Em andamento', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 9 (Desktop Acer)
    (9, NOW() - INTERVAL '16 days', 'Pendente', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 10 (Impressora Epson)
    (10, NOW() - INTERVAL '15 days', 'Pendente', 'Em andamento', 2), -- Funcionario Mário
    (10, NOW() - INTERVAL '13 days', 'Em andamento', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 11 (Teclado Dell)
    (11, NOW() - INTERVAL '12 days', 'Pendente', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 12 (Mouse Microsoft)
    (12, NOW() - INTERVAL '14 days', 'Pendente', 'Em andamento', 2), -- Funcionario Mário
    (12, NOW() - INTERVAL '10 days', 'Em andamento', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 13 (Desktop HP)
    (13, NOW() - INTERVAL '17 days', 'Pendente', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 14 (Notebook Samsung)
    (14, NOW() - INTERVAL '9 days', 'Pendente', 'Em andamento', 2), -- Funcionario Mário
    (14, NOW() - INTERVAL '7 days', 'Em andamento', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 15 (Impressora Brother)
    (15, NOW() - INTERVAL '11 days', 'Pendente', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 16 (Teclado HP)
    (16, NOW() - INTERVAL '10 days', 'Pendente', 'Em andamento', 1), -- Funcionario Maria
    (16, NOW() - INTERVAL '8 days', 'Em andamento', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 17 (Mouse Dell)
    (17, NOW() - INTERVAL '13 days', 'Pendente', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 18 (Notebook HP)
    (18, NOW() - INTERVAL '15 days', 'Pendente', 'Em andamento', 2), -- Funcionario Mário
    (18, NOW() - INTERVAL '12 days', 'Em andamento', 'Finalizado', 1), -- Funcionario Maria

    -- Solicitação 19 (Desktop Dell)
    (19, NOW() - INTERVAL '14 days', 'Pendente', 'Finalizado', 2), -- Funcionario Mário

    -- Solicitação 20 (Impressora Samsung)
    (20, NOW() - INTERVAL '10 days', 'Pendente', 'Em andamento', 1), -- Funcionario Maria
    (20, NOW() - INTERVAL '8 days', 'Em andamento', 'Finalizado', 2); -- Funcionario Mário
