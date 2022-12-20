package pl.ag.fleet.user;

import pl.ag.fleet.common.VehicleId;

public interface VehicleAvailabilityService {

  boolean isAvailable(VehicleId vehicleId);
}
