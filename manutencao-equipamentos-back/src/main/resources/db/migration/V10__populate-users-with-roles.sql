-- Inserção de usuários na tabela users com senha e salt compatíveis com SHA256PasswordEncoder
INSERT INTO users (id, nome, email, password_hash_salt)
VALUES
    ('97436adf-c107-4939-9cef-31d11c9a96dc', 'Matheus Kuss','matheuskuss@hotmail.com', 'Z5CsAiejDx8lDUUvHHrsgw==:N1+/FAci+JyF2RgtM9pfFlAPjv0Yd0l5WSnD5ujXMxs='), -- senha: 2301
    ('71adaa53-990a-486c-ad1c-265eae6f3a7f','Maria', 'maria@empresa.com', 'owLiNMae8xnwUlWFf56vOg==:eAbRmDOGpkPHJsXsVJDqEF6MjKulTzAbPbNdYJgaSsA='), -- senha: 1234
    ('382aad32-bd4e-4b2d-bffe-d10379e3e1c2','Mario', 'mario@empresa.com', 'RIcle8wEuO2tLdN1zEbS4Q==:G4HJ1Qen00YCNRYTTTdGu86uEObraOyO2eQ5ElxCItk='), -- senha: 4567
    ('22f111c2-2fbb-40a7-a11b-39c9e4f8d321', 'Joao','joao@cliente.com', 'Vn2oJ+9X0a7LSQVxd8AmsA==:WXRzPjvrhDXUjFnyPLLO8Qsie2ae0uo44u+Oyh/eZpU='), -- senha: 9123
    ('c7eae989-5f2e-41df-8121-799e5c9b5e52', 'Jose','jose@cliente.com', 'mBqu2ZSEz4uou5UljHfYwg==:1oe85ZBYtg1eQZwIpPlFEWYsKOMnBeBwtTrRR7wthLE='), -- senha: 8942
    ('d4c89b5a-8a0d-4383-aeb6-c8cb5f6d8ea1', 'Joana','joana@cliente.com', 'AIKGUSpSxuXtYrwd/5+I8g==:qopTsI77CgBI1A7Pq1JEzY7XXyfIf1e9+m6T+E1fSFs='), -- senha: 4564
    ('ad8f7685-9d9e-4f4e-88b8-bf9c7d812fbb', 'Joaquina','joaquina@cliente.com', 'q7e/aaZAaFeIHehaJz3IMA==:c/b7ph6sZvw8tlamcTZJ/RPIHzmU0RfR59JZ1xljVJo='); -- senha: 8978


-- Associação de usuários com suas roles na tabela user_roles
INSERT INTO user_roles (user_id, role)
VALUES
    ('97436adf-c107-4939-9cef-31d11c9a96dc', 'ADMIN'), -- Matheus Kuss
    ('71adaa53-990a-486c-ad1c-265eae6f3a7f', 'EMPLOYEE'), -- Maria
    ('382aad32-bd4e-4b2d-bffe-d10379e3e1c2', 'EMPLOYEE'), -- Mário
    ('22f111c2-2fbb-40a7-a11b-39c9e4f8d321', 'CLIENT'),     -- João
    ('c7eae989-5f2e-41df-8121-799e5c9b5e52', 'CLIENT'),     -- José
    ('d4c89b5a-8a0d-4383-aeb6-c8cb5f6d8ea1', 'CLIENT'),     -- Joana
    ('ad8f7685-9d9e-4f4e-88b8-bf9c7d812fbb', 'CLIENT'); -- Joaquina
