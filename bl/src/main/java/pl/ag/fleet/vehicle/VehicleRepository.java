package pl.ag.fleet.vehicle;

public interface VehicleRepository {

  void save(Vehicle vehicle);

  Vehicle load(VehicleId vehicleId);
}
