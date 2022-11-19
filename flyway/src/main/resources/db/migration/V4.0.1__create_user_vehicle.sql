create table if not exists fleet.user_vehicle
(
    id
    BIGINT
    generated
    by
    default as
    identity
    PRIMARY
    KEY,
    vehicle_id
    varchar
(
    255
),
    user_vehicle_id BIGINT
    );

alter table if exists fleet.user_vehicle
    add constraint FKCompanyUserVehicle
    foreign key (user_vehicle_id)
    references fleet.company_user