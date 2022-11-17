package pl.ag.fleet.application.driver;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.driver.DriverDTO;
import pl.ag.fleet.driver.DriverId;
import pl.ag.fleet.driver.DriverService;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

  private final DriverService driverService;

  @PostMapping
  public ResponseEntity<DriverResponse> createDriver(@RequestBody DriverDTO request) {
    val result = this.driverService.createDriver(request);
    return ResponseEntity.ok(new DriverResponse(result.isSuccess(), result.getReason()));
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
}