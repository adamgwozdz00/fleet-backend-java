package pl.ag.fleet.acl.vehicle;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.driver.DriverProvider;
import pl.ag.fleet.vehicle.DriverAvailabilityService;
import pl.ag.fleet.vehicle.DriverId;

@Component
@RequiredArgsConstructor
public class DriverAvailabilityServiceImpl implements DriverAvailabilityService {

  private final DriverProvider driverProvider;

  @Override
  public boolean isAvailableAt(DriverId driverId, LocalDateTime time) {
    return driverProvider.isDriverAvailableAt(driverId.getId(), time);
  }
}
