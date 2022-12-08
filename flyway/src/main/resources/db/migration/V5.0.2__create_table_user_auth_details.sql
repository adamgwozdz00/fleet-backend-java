create table if not exists fleet.auth_user
(
    id
    BIGINT
    generated
    by
    default as
    identity
    PRIMARY
    KEY,
    username
    VARCHAR
(
    255
) UNIQUE,
    password VARCHAR
(
    255
),
    role VARCHAR
(
    10
),
    company_id int8
    );

