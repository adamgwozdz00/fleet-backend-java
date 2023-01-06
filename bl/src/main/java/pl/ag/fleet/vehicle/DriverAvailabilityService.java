package pl.ag.fleet.vehicle;

import java.time.LocalDateTime;

public interface DriverAvailabilityService {

  boolean isAvailableAt(DriverId driverId, LocalDateTime time);
}
