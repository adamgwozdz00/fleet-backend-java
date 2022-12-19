package pl.ag.fleet.application.driver;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.driver.CompanyId;
import pl.ag.fleet.driver.DriverDTO;
import pl.ag.fleet.driver.DriverId;
import pl.ag.fleet.driver.DriverProvider;
import pl.ag.fleet.driver.DriverService;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

  private final DriverService driverService;
  private final DriverProvider driverProvider;
  private final AuthenticatedUserContextHolder contextHolder;

  @PostMapping
  public ResponseEntity<DriverResponse> createDriver(@RequestBody DriverDTO request) {
    val companyId = contextHolder.getAuthenticatedUser().getPrincipal().getCompanyId();
    val result = this.driverService.createDriver(new CompanyId(companyId),request);
    return ResponseEntity.ok(new DriverResponse(result.isSuccess(), result.getReason()));
  }

  @DeleteMapping("/{driverId}")
  public ResponseEntity<Void> createDriver(@PathVariable Long driverId) {
    this.driverService.deleteDriver(new DriverId(driverId));
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/promotions")
  public ResponseEntity<DriverResponse> promoteDriver(@RequestParam long driverId) {
    val result = this.driverService.promoteDriver(new DriverId(driverId));
    return ResponseEntity.ok(new DriverResponse(result.isSuccess(), result.getReason()));
  }

  @PatchMapping("/seniority")
  public ResponseEntity<DriverResponse> updateSeniority(@RequestParam long driverId,
      @RequestParam int seniorityInYears) {
    val result = this.driverService.updateDriverSeniority(new DriverId(driverId), seniorityInYears);
    return ResponseEntity.ok(new DriverResponse(result.isSuccess(), result.getReason()));
  }

  @GetMapping
  public ResponseEntity<Drivers> getAllDrivers() {
    val companyId = contextHolder.getAuthenticatedUser().getPrincipal().getCompanyId();
    return ResponseEntity.ok(new Drivers(this.driverProvider.getAllDrivers(companyId)));
  }

  @GetMapping("{driverId}")
  public ResponseEntity<DriverHistory> getDriverHistory(@PathVariable Long driverId) {
    return ResponseEntity.ok(new DriverHistory(this.driverProvider.getDriverHistory(driverId)));
  }
}
