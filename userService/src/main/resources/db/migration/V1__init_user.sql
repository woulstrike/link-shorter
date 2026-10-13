CREATE TABLE users (
    userId UUID PRIMARY KEY,
    user_name varchar(255) NOT NULL UNIQUE,
    password_hash_code varchar(255) NOT NULL,
    user_role varchar(255) NOT NULL DEFAULT "USER",
    user_status varchar(255) NOT NULL DEFAULT "ACTIVE",
    created_links TEXT[] NOT NULL,
    count_of_created_links INT NOT NULL DEFAULT 0,
    created_user_date timestamp NOT NULL,
    user_update_date timestamp NOT NULL
);
