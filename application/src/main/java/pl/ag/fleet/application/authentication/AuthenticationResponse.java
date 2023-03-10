package pl.ag.fleet.application.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String token;
  private long expiresIn;
  private String role;
}
