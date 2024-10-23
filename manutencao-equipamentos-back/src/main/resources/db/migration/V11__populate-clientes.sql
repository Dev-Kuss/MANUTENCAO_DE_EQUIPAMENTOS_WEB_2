INSERT INTO cliente (id, cpf, nome, telefone, cep, logradouro, numero, complemento, bairro, cidade, estado, user_id)
VALUES
    (uuid_generate_v4(), '12345678901', 'João', '111111111', '12345-678', 'Rua A', '100', 'Apt 1', 'Bairro A', 'Cidade A', 'Estado A', (SELECT id FROM users WHERE email = 'joao@empresa.com')),
    (uuid_generate_v4(), '12345678902', 'José', '222222222', '12345-678', 'Rua B', '200', '', 'Bairro B', 'Cidade B', 'Estado B', (SELECT id FROM users WHERE email = 'jose@empresa.com')),
    (uuid_generate_v4(), '12345678903', 'Joana', '333333333', '12345-678', 'Rua C', '300', 'Apt 2', 'Bairro C', 'Cidade C', 'Estado C', (SELECT id FROM users WHERE email = 'joana@empresa.com')),
    (uuid_generate_v4(), '12345678904', 'Joaquina', '444444444', '12345-678', 'Rua D', '400', '', 'Bairro D', 'Cidade D', 'Estado D', (SELECT id FROM users WHERE email = 'joaquina@empresa.com'));