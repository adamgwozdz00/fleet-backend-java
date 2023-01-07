alter table if exists fleet.vehicle
drop
constraint FKInsurance;

alter table if exists fleet.vehicle
drop
column insurance_id;

alter table if exists fleet.insurance
    add constraint FKInsurance
    foreign key (vehicle_id)
    references fleet.vehicle;

alter table if exists fleet.vehicle
drop
constraint FKOverview;

alter table if exists fleet.vehicle
drop
column overview_id;

alter table if exists fleet.overview
    add constraint FKOverview
    foreign key (vehicle_id)
    references fleet.vehicle;