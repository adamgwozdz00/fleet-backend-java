package pl.ag.fleet.application.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthenticatedUserContextHolder contextHolder;

  @GetMapping
  public ResponseEntity<UserInfo> getUserInfo() {
    val user = contextHolder.getAuthenticatedUser();
    return ResponseEntity.ok(
        new UserInfo(user.getPrincipal().getCompanyId(), user.getPrincipal().getRole()));
  }
}
