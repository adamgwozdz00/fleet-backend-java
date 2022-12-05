package pl.ag.fleet.manager.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResult {

  private boolean success;
  private Token token;
  private String role;

  static AuthResult createSuccess(Token token, String role) {
    return new AuthResult(true, token, role);
  }

  static AuthResult createFail() {
    return new AuthResult(false, null, null);
  }
}
