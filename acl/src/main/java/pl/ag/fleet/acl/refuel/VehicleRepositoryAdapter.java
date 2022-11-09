package pl.ag.fleet.acl.refuel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.refuel.VehicleId;
import pl.ag.fleet.vehicle.VehicleRepository;

@Component
@RequiredArgsConstructor
public class VehicleRepositoryAdapter implements pl.ag.fleet.refuel.VehicleRepository {

  private final VehicleRepository repository;


  @Override
  public boolean exists(VehicleId vehicleId) {
    return repository.load(new pl.ag.fleet.vehicle.VehicleId(vehicleId.getVehicleId())) != null;
  }
}
