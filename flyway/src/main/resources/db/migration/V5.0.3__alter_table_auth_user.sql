ALTER TABLE fleet.auth_user
    ADD COLUMN first_name VARCHAR(255);
ALTER TABLE fleet.auth_user
    ADD COLUMN last_name VARCHAR(255);
ALTER TABLE fleet.auth_user
    ADD COLUMN title VARCHAR(255);