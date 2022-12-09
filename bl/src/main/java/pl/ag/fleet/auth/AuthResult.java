package pl.ag.fleet.auth;

import lombok.Value;

@Value
public class AuthResult {

  private Long userId;
  private boolean success;

  static AuthResult createSuccess(long userId) {
    return new AuthResult(userId, true);
  }

  static AuthResult createFail() {
    return new AuthResult(null, false);
  }
}
