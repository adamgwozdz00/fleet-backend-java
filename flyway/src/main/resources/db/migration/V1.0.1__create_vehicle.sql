create table vehicle
(
    id                        BIGSERIAL PRIMARY KEY ,
    company_id                BIGINT,
    production_year           int4,
    fuel_type                 varchar(9),
    make                      varchar(255),
    model                     varchar(255),
    vin_number                varchar(255),
    insurance_cost            numeric(19, 2),
    insurance_expiration_date date,
    insurance_name            varchar(255),
    overview_cost             numeric(19, 2),
    overview_description      varchar(255),
    overview_expiration_date  date,
    overview_name             varchar(255),
    actual_driver_id          BIGINT,
    liters                    numeric(19, 2),
    kilometers                numeric(19, 2)
);