package pl.ag.fleet.acl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.VehicleId;
import pl.ag.fleet.user.VehicleAvailabilityService;
import pl.ag.fleet.vehicle.VehicleProvider;

@Component
@RequiredArgsConstructor
public class VehicleAvailabilityServiceAdapter implements VehicleAvailabilityService {

  private final VehicleProvider vehicleProvider;

  @Override
  public boolean isAvailable(VehicleId vehicleId) {
    return vehicleProvider.isVehicleAvailable(vehicleId.getVehicleId());
  }
}
