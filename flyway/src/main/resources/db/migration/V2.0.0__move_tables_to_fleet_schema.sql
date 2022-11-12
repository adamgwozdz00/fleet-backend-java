CREATE SCHEMA IF NOT EXISTS fleet;

ALTER TABLE vehicle
    SET SCHEMA fleet;

ALTER TABLE vehicle_state
    SET SCHEMA fleet;

ALTER TABLE insurance
    SET SCHEMA fleet;

ALTER TABLE overview
    SET SCHEMA fleet;

ALTER TABLE re_fuel
    SET SCHEMA fleet;