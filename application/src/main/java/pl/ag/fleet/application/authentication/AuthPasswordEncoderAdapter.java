package pl.ag.fleet.application.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.ag.fleet.auth.AuthPasswordEncoder;

@Component
@RequiredArgsConstructor
public class AuthPasswordEncoderAdapter implements AuthPasswordEncoder {

  private final PasswordEncoder passwordEncoder;

  @Override
  public String encode(String password) {
    return passwordEncoder.encode(password);
  }
}