package pl.ag.fleet.driver;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.DriverId;

@Service
@RequiredArgsConstructor
public class DriverService {

  private final DriverRepository repository;

  public DriverOperationResult createDriver(CompanyId companyId, DriverDTO driver) {
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

  public DriverOperationResult updateDriver(DriverSeniorityDTO driverSeniorityDTO) {
    System.out.println(driverSeniorityDTO);
    try {
      val driver = repository.load(new DriverId(driverSeniorityDTO.getDriverId()));
      switch (driverSeniorityDTO.getOperation()) {
        case PROMOTION:
          driver.promote();
          break;
        case INCREMENT:
          driver.updateSeniority(driver.getSeniority().increment());
          break;
        case DECREMENT:
          driver.updateSeniority(driver.getSeniority().decrement());
          break;
        default:
          driver.updateSeniority(new Seniority(driverSeniorityDTO.getSeniority()));
          break;
      }
      repository.save(driver);
      return new DriverOperationResult();
    } catch (RuntimeException ex) {
      return new DriverOperationResult(ex.getMessage());
    }
  }

  public void deleteDriver(DriverId driverId) {
    val driver = repository.load(driverId);
    if (driver == null) {
      throw new IllegalStateException("Vehicle not exists");
    }
    repository.delete(driver);
  }
}
