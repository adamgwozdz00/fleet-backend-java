package pl.ag.fleet.manager.security;

import lombok.Value;

@Value
public class JWTDecodedUser {
  private String userId;
  private String role;
  private Long companyId;
}
