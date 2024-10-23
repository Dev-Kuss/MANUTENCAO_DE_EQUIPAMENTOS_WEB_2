CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       password_salt VARCHAR(255) NOT NULL
);

CREATE TABLE user_roles (
                            user_id BIGSERIAL NOT NULL,
                            role VARCHAR(255) NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
