package pl.ag.fleet.driver;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {

  private final DriverRepository repository;

  public DriverOperationResult createDriver(CompanyId companyId,DriverDTO driver) {
    try {
      this.repository.save(
          new Driver(
              companyId,
              new DriverDetails(driver.getFirstName(), driver.getLastName()),
              new Seniority(driver.getSeniorityInYears())
          ));
      return new DriverOperationResult();
    } catch (RuntimeException ex) {
      return new DriverOperationResult(ex.getMessage());
    }
  }

  public DriverOperationResult promoteDriver(DriverId driverId) {
    try {
      val driver = repository.load(driverId);
      driver.promote();
      repository.save(driver);
      return new DriverOperationResult();
    } catch (RuntimeException ex) {
      return new DriverOperationResult(ex.getMessage());
    }
  }

  public DriverOperationResult updateDriverSeniority(DriverId driverId, int seniority) {
    try {
      val driver = repository.load(driverId);
      driver.updateSeniority(new Seniority(seniority));
      repository.save(driver);
      return new DriverOperationResult();
    } catch (RuntimeException ex) {
      return new DriverOperationResult(ex.getMessage());
    }
  }

  public void deleteDriver(DriverId driverId) {
    val driver = repository.load(driverId);
    if(driver == null){
      throw new IllegalStateException("Vehicle not exists");
    }
    repository.delete(driver);
  }
}
