package pl.ag.fleet.acl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.user.VehicleAvailabilityService;
import pl.ag.fleet.user.VehicleId;
import pl.ag.fleet.vehicle.VehicleProvider;

@Component
@RequiredArgsConstructor
public class VehicleAvailabilityServiceAdapter implements VehicleAvailabilityService {

  private VehicleProvider vehicleProvider;

  @Override
  public boolean isAvailable(VehicleId vehicleId) {
    return vehicleProvider.isVehicleAvailable();
  }
}
