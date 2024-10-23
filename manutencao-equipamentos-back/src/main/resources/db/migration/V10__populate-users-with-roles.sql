-- Criar usuários com base na nova estrutura de tabela `users` e associar roles (USER, ADMIN, EMPLOYEE)

-- Inserindo usuários na tabela users
INSERT INTO users (id, email, password_hash, password_salt)
VALUES
    (1, 'maria@empresa.com', '$2a$10$AbCdEfGhIjKlMnOpQrSt', 'saltMaria'),  -- Senha exemplo: "senhaMaria" com salt
    (2, 'mario@empresa.com', '$2a$10$WxYz0123456789ABCDEF', 'saltMario'),  -- Senha exemplo: "senhaMario" com salt
    (3, 'joao@empresa.com', '$2a$10$1q2w3e4r5t6y7u8i9o0p', 'saltJoao'),    -- Senha exemplo: "senhaJoao" com salt
    (4, 'jose@empresa.com', '$2a$10$XyZ0aBcDeFgHiJkLmNoP', 'saltJose'),    -- Senha exemplo: "senhaJose" com salt
    (5, 'joana@empresa.com', '$2a$10$QwErTyUiOpAsDfGhJkL', 'saltJoana'),   -- Senha exemplo: "senhaJoana" com salt
    (6, 'joaquina@empresa.com', '$2a$10$MnBvCdFrTgYhUiOjPqW', 'saltJoaquina');  -- Senha exemplo: "senhaJoaquina" com salt

-- Associando roles (USER, ADMIN, EMPLOYEE)
-- Maria será ADMIN e EMPLOYEE, Mario será EMPLOYEE, os demais serão USER.
INSERT INTO user_roles (user_id, role)
VALUES
    (1, 'ADMIN'),
    (1, 'EMPLOYEE'),
    (2, 'EMPLOYEE'),
    (3, 'USER'),
    (4, 'USER'),
    (5, 'USER'),
    (6, 'USER');
