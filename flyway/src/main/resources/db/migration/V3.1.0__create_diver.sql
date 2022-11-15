CREATE TABLE IF NOT EXISTS fleet.driver
(
    id
    bigint
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    company_id
    bigint
    NOT
    NULL,
    last_name
    varchar
(
    255
),
    first_name varchar
(
    255
),
    seniority integer NOT NULL,
    title varchar
(
    255
)
    );
