create table fleet.company_user
(
    id         BIGINT generated by default as identity PRIMARY KEY,
    company_id int8,
    user_id    int8 not null
);
