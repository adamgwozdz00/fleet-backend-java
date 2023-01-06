alter table if exists fleet.vehicle
drop
constraint FKVehicleState;

alter table if exists fleet.vehicle
drop
column state_id;

alter table if exists fleet.vehicle_state
    add constraint FKVehicleState
    foreign key (vehicle_id)
    references fleet.vehicle;