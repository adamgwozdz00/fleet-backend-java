package pl.ag.fleet.manager.security;

import lombok.Value;

@Value
public class PrincipalData {

  private String userId;
  private Long companyId;
  private UserRole role;
}
