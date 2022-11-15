package pl.ag.fleet.driver;

public interface DriverRepository {

  Driver load(DriverId driverId);

  void save(Driver driver);
}
