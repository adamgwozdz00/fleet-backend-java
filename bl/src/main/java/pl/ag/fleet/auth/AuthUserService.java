package pl.ag.fleet.auth;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthUserService {

  private final AuthUserRepository authUserRepository;
  private final AuthPasswordEncoder passwordEncoder;

  public AuthResult register(AuthUser authUser) {
    try {
      authUser.encodePassword(passwordEncoder);
      val result = this.authUserRepository.save(authUser);
      return AuthResult.createSuccess(result.getId());
    } catch (RuntimeException r) {
      return AuthResult.createFail();
    }
  }
}
