package pl.ag.fleet.refuel;

public interface VehicleRepository {

  boolean exists(VehicleId vehicleId);
}
