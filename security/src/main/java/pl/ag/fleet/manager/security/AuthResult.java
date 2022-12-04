package pl.ag.fleet.manager.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResult {

  private boolean success;
  private Token token;

  static AuthResult createSuccess(Token token) {
    return new AuthResult(true, token);
  }

  static AuthResult createFail() {
    return new AuthResult(false, null);
  }
}
