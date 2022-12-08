package pl.ag.fleet.application.user;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.user.CompanyId;
import pl.ag.fleet.user.CompanyUserService;
import pl.ag.fleet.user.UserId;
import pl.ag.fleet.user.UsersProvider;
import pl.ag.fleet.user.VehicleUserDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthenticatedUserContextHolder contextHolder;
  private final CompanyUserService companyUserService;
  private final UsersProvider userProvider;
  private final UsersDataResponseFactory responseFactory;

  private final UserDataFactory userDataFactory;

  @GetMapping("/self")
  public ResponseEntity<SelfUserData> getUserData() {
    val user = contextHolder.getAuthenticatedUser();
    return ResponseEntity.ok(userDataFactory.createData(user));
  }

  @GetMapping
  public ResponseEntity<UsersData> getAllUsers() {
    val user = contextHolder.getAuthenticatedUser().getPrincipal();
    return this.responseFactory.create(userProvider.getAllUsersByCompanyId(user.getCompanyId()),
        user.getUserId());
  }

  @PostMapping
  public ResponseEntity<Boolean> createCompanyUser(
      @Valid @RequestBody CreateCompanyUserRequest body) {
    val user = contextHolder.getAuthenticatedUser().getPrincipal();
    val result = this.companyUserService.createUser(new UserId(body.userId),
        new CompanyId(user.getCompanyId()));
    return ResponseEntity.ok(result.isSuccess());
  }

  @PatchMapping("/vehicles/{vehicleId}")
  public ResponseEntity<Boolean> addVehicleToUser(@PathVariable String vehicleId) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();
    val result = this.companyUserService.addVehicleToUser(
        new VehicleUserDTO(principal.getUserId(), vehicleId, principal.getCompanyId()));
    return ResponseEntity.ok(result.isSuccess());
  }

  @DeleteMapping("/vehicles/{vehicleId}")
  public ResponseEntity<Void> removeVehicle(@PathVariable String vehicleId) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();
    this.companyUserService.removeVehicleFormUser(
        new VehicleUserDTO(principal.getUserId(), vehicleId, principal.getCompanyId()));
    return ResponseEntity.ok().build();
  }
}
