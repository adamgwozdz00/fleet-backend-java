package pl.ag.fleet.application.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.user.CompanyUserService;
import pl.ag.fleet.user.VehicleUserDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthenticatedUserContextHolder contextHolder;
  private final CompanyUserService companyUserService;

  @GetMapping
  public ResponseEntity<UserInfo> getUserInfo() {
    val user = contextHolder.getAuthenticatedUser();
    return ResponseEntity.ok(
        new UserInfo(user.getPrincipal().getCompanyId(), user.getPrincipal().getRole()));
  }

  @PatchMapping("/vehicles/{vehicleId}")
  public ResponseEntity<Boolean> addVehicleToUser(@PathVariable String vehicleId) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();
    val result = this.companyUserService.addVehicleToUser(
        new VehicleUserDTO(principal.getUserId(), vehicleId, principal.getCompanyId()));
    return ResponseEntity.ok(result.isSuccess());
  }

  @DeleteMapping("/vehicles/{vehicleId}")
  public ResponseEntity<Void> removeVehicleToUser(@PathVariable String vehicleId) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();
    this.companyUserService.removeVehicleFormUser(
        new VehicleUserDTO(principal.getUserId(), vehicleId, principal.getCompanyId()));
    return ResponseEntity.ok().build();
  }
}
