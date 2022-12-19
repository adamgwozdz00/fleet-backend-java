package pl.ag.fleet.db.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.vehicle.Vehicle;
import pl.ag.fleet.vehicle.VehicleId;
import pl.ag.fleet.vehicle.VehicleRepository;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements VehicleRepository {

  private final VehicleJpa jpa;

  @Override
  public void save(Vehicle vehicle) {
    jpa.save(vehicle);
  }

  @Override
  public Vehicle load(VehicleId vehicleId) {
    return jpa.findById(vehicleId).orElse(null);
  }

  @Override
  public void delete(Vehicle vehicle) {
    this.jpa.delete(vehicle);
  }
}
