CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id                 UUID PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    nome               VARCHAR(255),
    email              VARCHAR(255)                                NOT NULL UNIQUE,
    password_hash_salt VARCHAR(255)                                NOT NULL
);

CREATE TABLE user_roles
(
    user_id UUID         NOT NULL,
    role    VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);