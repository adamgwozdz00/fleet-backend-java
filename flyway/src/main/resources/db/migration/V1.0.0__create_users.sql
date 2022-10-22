CREATE TABLE APP_USER
(
    id       BIGSERIAL PRIMARY KEY,
    username TEXT unique,
    password TEXT,
    role     TEXT
);