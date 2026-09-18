CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_users_username (username)
);