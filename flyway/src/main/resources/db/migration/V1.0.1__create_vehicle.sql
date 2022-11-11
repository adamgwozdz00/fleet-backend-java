CREATE TABLE vehicle
(
    vehicle_id      character varying(255) PRIMARY KEY,
    company_id      bigint,
    production_year integer,
    fuel_type       character varying(10),
    make            character varying(255),
    model           character varying(255),
    vin_number      character varying(255),
    insurance_id    bigint,
    overview_id     bigint,
    state_id        bigint
);

