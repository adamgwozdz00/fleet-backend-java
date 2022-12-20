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

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthenticatedUserContextHolder contextHolder;
  private final CompanyUserService companyUserService;
  private final UsersProvider userProvider;
  private final UsersDataResponseFactory responseFactory;

  private final UserDataFactory userDataFactory;

  private final UserRequestFactory userRequestFactory;

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

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteCompanyUser(@PathVariable Long userId) {
    this.companyUserService.deleteUser(new UserId(userId));
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/vehicles")
  public ResponseEntity<Boolean> addVehicleToUser(UserParams userParams) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();

    val request = userRequestFactory.create(userParams, principal.getCompanyId());

    val result = this.companyUserService.addVehicleToUser(request);
    return ResponseEntity.ok(result.isSuccess());
  }

  @DeleteMapping("/vehicles")
  public ResponseEntity<Void> removeVehicle(UserParams userParams) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();

    val request = userRequestFactory.create(userParams, principal.getCompanyId());

    this.companyUserService.removeVehicleFormUser(request);
    return ResponseEntity.ok().build();
  }
}
