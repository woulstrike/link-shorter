CREATE TABLE users (
    userId UUID PRIMARY KEY,
    user_name varchar(255) NOT NULL UNIQUE,
    password_hash_code varchar(255) NOT NULL,
    created_links TEXT[] NOT NULL,
    count_of_created_links INT NOT NULL DEFAULT 0
)