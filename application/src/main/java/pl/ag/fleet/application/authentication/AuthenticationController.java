package pl.ag.fleet.application.authentication;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.manager.security.LoginDetails;
import pl.ag.fleet.manager.security.SecurityService;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final SecurityService service;
  private final AuthenticatedUserContextHolder contextHolder;

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    val result = this.service.authenticate(
        new LoginDetails(loginRequest.getUsername(), loginRequest.getPassword()));
    return new AuthenticationResponse(result.getToken().getToken(),
        result.getToken().getExpiresIn(), result.getRole());
  }

  @GetMapping("/roles")
  public AuthenticationResponse getUserRole() {
    val role = contextHolder.getAuthenticatedUser().getPrincipal().getRole();
    return new AuthenticationResponse("", 0, role.getRoleName());
  }
}
