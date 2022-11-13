package pl.ag.fleet.application.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.JWTUserDecoderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final JWTUserDecoderService jwtUserDecoderService;


  @GetMapping
  public ResponseEntity<UserInfo> getUserInfo(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwtToken){
    val user = jwtUserDecoderService.decode(jwtToken);
    return ResponseEntity.ok(new UserInfo(user.getCompanyId(),user.getRole()));
  }
}
