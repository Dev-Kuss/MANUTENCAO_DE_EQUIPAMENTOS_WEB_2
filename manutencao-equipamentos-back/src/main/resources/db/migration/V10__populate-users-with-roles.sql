-- Inserção de usuários na tabela users com senha e salt compatíveis com SHA256PasswordEncoder
INSERT INTO users (id, nome, email, password_hash_salt)
VALUES
    ('97436adf-c107-4939-9cef-31d11c9a96dc', 'Matheus Kuss','matheuskuss@hotmail.com', 'Z5CsAiejDx8lDUUvHHrsgw==:N1+/FAci+JyF2RgtM9pfFlAPjv0Yd0l5WSnD5ujXMxs='), -- senha: 2301
    ('71adaa53-990a-486c-ad1c-265eae6f3a7f','Maria', 'maria@empresa.com', 'MTEyMjM=:XkOBHDMI0HEDyka9PbQUtbXRYK2s9ixtA0a8+6p1VzM='), -- senha:
    ('382aad32-bd4e-4b2d-bffe-d10379e3e1c2','Mario', 'mario@empresa.com', 'MjEyMTI=:oeH49/WTcf+QiKrmiWbHEKPjBz5dUD5dGyaAt4soBFA='), -- senha:
    ('22f111c2-2fbb-40a7-a11b-39c9e4f8d321', 'Joao','joao@cliente.com', 'MzEyMzI=:PiOBj5RZ0dso93u5ka1XlNYETyF6Hd71L7ubOYOlRY8='), -- senha:
    ('c7eae989-5f2e-41df-8121-799e5c9b5e52', 'Jose','jose@cliente.com', 'NDEyMzM=:Doh13YFL+/xMcbJgSeDp1Ghd9PMBlb0PfCG0h9LcyJA='), -- senha:
    ('d4c89b5a-8a0d-4383-aeb6-c8cb5f6d8ea1', 'Joana','joana@cliente.com', 'NTIyMTQ=:PmExWcbo7jx6ZaEr28uMW5LqZJGRbwx0rTS5REuJWfg='), -- senha:
    ('ad8f7685-9d9e-4f4e-88b8-bf9c7d812fbb', 'Joaquina','joaquina@cliente.com', 'NjEyMTU=:mfWjG81yJdwHxWLLgj8flPaUpBvRzxLi0B5Zo7C8pbU='); -- senha:


-- Associação de usuários com suas roles na tabela user_roles
INSERT INTO user_roles (user_id, role)
VALUES
    ('97436adf-c107-4939-9cef-31d11c9a96dc', 'ADMIN'), -- Matheus Kuss
    ('71adaa53-990a-486c-ad1c-265eae6f3a7f', 'ADMIN'), -- Maria
    ('382aad32-bd4e-4b2d-bffe-d10379e3e1c2', 'ADMIN'), -- Mário
    ('22f111c2-2fbb-40a7-a11b-39c9e4f8d321', 'USER'),     -- João
    ('c7eae989-5f2e-41df-8121-799e5c9b5e52', 'USER'),     -- José
    ('d4c89b5a-8a0d-4383-aeb6-c8cb5f6d8ea1', 'USER'),     -- Joana
    ('ad8f7685-9d9e-4f4e-88b8-bf9c7d812fbb', 'USER'); -- Joaquina
