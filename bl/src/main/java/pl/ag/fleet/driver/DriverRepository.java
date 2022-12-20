package pl.ag.fleet.driver;

import pl.ag.fleet.common.DriverId;

public interface DriverRepository {

  Driver load(DriverId driverId);

  void save(Driver driver);

  void delete(Driver driver);
}
