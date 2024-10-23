INSERT INTO funcionario (nome, telefone, data_nascimento, id)
VALUES
    ('Maria', '555555555', '1985-07-15', (SELECT id FROM users WHERE email = 'maria@empresa.com')),
    ('Mário', '666666666', '1990-06-10', (SELECT id FROM users WHERE email = 'mario@empresa.com'));
