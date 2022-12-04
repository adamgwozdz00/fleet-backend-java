package pl.ag.fleet.manager.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
class User {

  private UserId userId;

  private Username username;
  private Password password;
  private UserRole role;
  private CompanyId companyId;

  boolean isPasswordMatches(Password password, PasswordEncoder encoder) {
    return encoder.matches(password.getPassword(), this.password.getPassword());
  }

}
