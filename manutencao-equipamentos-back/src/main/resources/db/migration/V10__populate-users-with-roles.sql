-- V10__populate-users.sql

-- Inserção de usuários na tabela users
INSERT INTO users (id, email, password_hash, password_salt)
VALUES ('71adaa53-990a-486c-ad1c-265eae6f3a7f', 'maria@empresa.com', '5f4dcc3b5aa765d61d8327deb882cf99','a1b2c3d4e5f6'), -- senha: password1
       ('382aad32-bd4e-4b2d-bffe-d10379e3e1c2', 'mario@empresa.com', 'e99a18c428cb38d5f260853678922e03','f7g8h9i0j1k2'), -- senha: password2
       ('22f111c2-2fbb-40a7-a11b-39c9e4f8d321', 'joao@cliente.com', '25d55ad283aa400af464c76d713c07ad','l3m4n5o6p7q8'), -- senha: password3
       ('c7eae989-5f2e-41df-8121-799e5c9b5e52', 'jose@cliente.com', '098f6bcd4621d373cade4e832627b4f6','r9s0t1u2v3w4'), -- senha: password4
       ('d4c89b5a-8a0d-4383-aeb6-c8cb5f6d8ea1', 'joana@cliente.com', 'b1d27a703f7ef9d697f2837b7d0e322e','x5y6z7a8b9c0'), -- senha: password5
       ('ad8f7685-9d9e-4f4e-88b8-bf9c7d812fbb', 'joaquina@cliente.com', 'c4ca4238a0b923820dcc509a6f75849b','d1e2f3g4h5i6');-- senha: password6

-- Associação de usuários com suas roles na tabela user_roles
INSERT INTO user_roles (user_id, role)
VALUES ('71adaa53-990a-486c-ad1c-265eae6f3a7f', 'FUNCIONARIO'), -- Maria
       ('382aad32-bd4e-4b2d-bffe-d10379e3e1c2', 'FUNCIONARIO'), -- Mário
       ('22f111c2-2fbb-40a7-a11b-39c9e4f8d321', 'CLIENTE'),     -- João
       ('c7eae989-5f2e-41df-8121-799e5c9b5e52', 'CLIENTE'),     -- José
       ('d4c89b5a-8a0d-4383-aeb6-c8cb5f6d8ea1', 'CLIENTE'),     -- Joana
       ('ad8f7685-9d9e-4f4e-88b8-bf9c7d812fbb', 'CLIENTE'); -- Joaquina
