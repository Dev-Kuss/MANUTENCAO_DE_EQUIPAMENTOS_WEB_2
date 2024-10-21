-- Inserção de novos usuários na tabela 'users'
INSERT INTO users (id, email, password)
VALUES (1, 'admin@example.com', 'senha_criptografada1'),
       (2, 'manager@example.com', 'senha_criptografada2'),
       (3, 'user@example.com', 'senha_criptografada3'),
       (4, 'viewer@example.com', 'senha_criptografada4');

-- Inserção de papéis (roles) para os usuários na tabela 'user_roles'
-- Supondo que os papéis (roles) sejam strings como 'ADMIN', 'MANAGER', 'USER', 'VIEWER'
INSERT INTO user_roles (user_id, role)
VALUES (1, 'ADMIN'),   -- admin -> ROLE_ADMIN
       (1, 'USER'),    -- admin -> ROLE_USER
       (2, 'MANAGER'), -- manager -> ROLE_MANAGER
       (2, 'USER'),    -- manager -> ROLE_USER
       (3, 'USER'),    -- user -> ROLE_USER
       (4, 'VIEWER'); -- viewer -> ROLE_VIEWER
