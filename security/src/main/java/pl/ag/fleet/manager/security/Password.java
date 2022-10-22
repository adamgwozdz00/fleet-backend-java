package pl.ag.fleet.manager.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
class Password {

  private String password;

  Password encode(PasswordEncoder encoder) {
    return new Password(encoder.encode(this.password));
  }
}
