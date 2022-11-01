package pl.ag.fleet.manager.security;

import lombok.Value;

@Value
public class AuthResult {

  private boolean success;
  private String token;
  private String role;
}
