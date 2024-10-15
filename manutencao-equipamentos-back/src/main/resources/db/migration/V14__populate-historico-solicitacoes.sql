INSERT INTO historico_solicitacao (id_solicitacao, data_hora, estado_anterior, estado_atual, id_funcionario)
VALUES (1, '2024-01-02 12:00:00', 'ABERTA', 'ORÇADA', 1),
       (2, '2024-01-03 14:00:00', 'ORÇADA', 'APROVADA', 2),
       (3, '2024-01-04 16:00:00', 'APROVADA', 'REJEITADA', 1),
       (4, '2024-01-05 18:00:00', 'REJEITADA', 'AGUARDANDO PAGAMENTO', 2),
       (5, '2024-01-06 10:00:00', 'AGUARDANDO PAGAMENTO', 'PAGA', 1),
       (6, '2024-01-07 13:00:00', 'PAGA', 'FINALIZADA', 2),
       (7, '2024-01-08 09:00:00', 'ABERTA', 'ORÇADA', 1),
       (8, '2024-01-09 15:00:00', 'ORÇADA', 'APROVADA', 2),
       (9, '2024-01-10 11:00:00', 'APROVADA', 'REJEITADA', 1),
       (10, '2024-01-11 17:00:00', 'REJEITADA', 'AGUARDANDO PAGAMENTO', 2);