package pl.ag.fleet.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthUserService {

  private final AuthUserRepository authUserRepository;
  private final AuthPasswordEncoder passwordEncoder;

  public AuthResult register(AuthUser authUser) {
    try {
      authUser.encodePassword(passwordEncoder);
      this.authUserRepository.save(authUser);
      return new AuthResult(true);
    } catch (RuntimeException r) {
      return new AuthResult(false);
    }
  }
}
