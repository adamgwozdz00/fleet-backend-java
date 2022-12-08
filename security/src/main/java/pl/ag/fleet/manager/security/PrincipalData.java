package pl.ag.fleet.manager.security;

import lombok.Value;

@Value
public class PrincipalData {

  private long userId;
  private String username;
  private Long companyId;
  private UserRole role;
}
