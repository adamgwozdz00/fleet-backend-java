package pl.ag.fleet.vehicle;

import pl.ag.fleet.common.VehicleId;

public interface VehicleRepository {

  void save(Vehicle vehicle);

  Vehicle load(VehicleId vehicleId);

  void delete(Vehicle vehicle);
}
