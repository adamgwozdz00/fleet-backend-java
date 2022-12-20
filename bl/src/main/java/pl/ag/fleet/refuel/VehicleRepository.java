package pl.ag.fleet.refuel;

import pl.ag.fleet.common.VehicleId;

public interface VehicleRepository {

  boolean exists(VehicleId vehicleId);
}
