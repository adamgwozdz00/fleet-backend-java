package pl.ag.fleet.manager.security;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

  private final PasswordEncoder encoder;
  private final TokenService tokenService;
  private final UserRepository repository;

  public AuthResult authenticate(LoginDetails details) {
    val user = repository.findByUsername(new Username(details.getUsername())).orElseThrow();
    if (user.isPasswordMatches(new Password(details.getPassword()), encoder)) {
      return new AuthResult(true, tokenService.generateToken(details.getUsername()).getToken(),
          details.getUsername());
    }
    return new AuthResult(false, "", "");
  }
}
