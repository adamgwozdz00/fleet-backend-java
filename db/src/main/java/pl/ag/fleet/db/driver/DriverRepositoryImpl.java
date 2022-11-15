package pl.ag.fleet.db.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.driver.Driver;
import pl.ag.fleet.driver.DriverId;
import pl.ag.fleet.driver.DriverRepository;

@Repository
@RequiredArgsConstructor
public class DriverRepositoryImpl implements DriverRepository {

  private final DriverJpa driverJpa;


  @Override
  public Driver load(DriverId driverId) {
    return driverJpa.findById(driverId.getDriverId())
        .orElseThrow(() -> new IllegalStateException("Driver not found."));
  }

  @Override
  public void save(Driver driver) {
    driverJpa.save(driver);
  }
}
