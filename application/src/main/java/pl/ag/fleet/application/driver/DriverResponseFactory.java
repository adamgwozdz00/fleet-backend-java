package pl.ag.fleet.application.driver;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.Availability;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.driver.DriverProvider;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;

@Component
@RequiredArgsConstructor
public class DriverResponseFactory {

  private final AuthenticatedUserContextHolder contextHolder;

  public Drivers create(Availability availability, DriverProvider driverProvider) {
    val companyId = new CompanyId(
        contextHolder.getAuthenticatedUser().getPrincipal().getCompanyId());
    val data = driverProvider.getAllDrivers(companyId, availability);
    return new Drivers(data);
  }
}
