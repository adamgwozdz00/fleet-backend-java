package pl.ag.fleet.vehicle;

public interface VehicleRepository {

  void save(Vehicle vehicle);

  Vehicle load(VehicleId vehicleId);

  void delete(Vehicle vehicle);
}
