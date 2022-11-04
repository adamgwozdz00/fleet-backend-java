package pl.ag.fleet.vehicle;

import java.util.HashMap;

class MockVehicleRepository implements VehicleRepository {

  HashMap<VehicleId, Vehicle> vehicles;

  MockVehicleRepository() {
    vehicles = new HashMap<>();
  }


  @Override
  public void save(Vehicle vehicle) {
    this.vehicles.put(vehicle.getId(), vehicle);
  }

  @Override
  public Vehicle load(VehicleId vehicleId) {
    return this.vehicles.get(vehicleId);
  }
}
