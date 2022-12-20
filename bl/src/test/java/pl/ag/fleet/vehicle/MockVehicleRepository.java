package pl.ag.fleet.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import pl.ag.fleet.common.VehicleId;


class MockVehicleRepository implements VehicleRepository {

  ConcurrentHashMap<VehicleId, Vehicle> vehicles;
  List<SaveLoadEvent> events;

  MockVehicleRepository() {
    vehicles = new ConcurrentHashMap<>();
    events = new ArrayList<>();
  }


  @Override
  public void save(Vehicle vehicle) {
    this.events.add(SaveLoadEvent.END);
    this.vehicles.put(vehicle.getId(), vehicle);
  }

  @Override
  public Vehicle load(VehicleId vehicleId) {
    this.events.add(SaveLoadEvent.START);
    return this.vehicles.get(vehicleId);
  }

  @Override
  public void delete(Vehicle vehicle) {

  }

  enum SaveLoadEvent {
    START, END
  }
}
