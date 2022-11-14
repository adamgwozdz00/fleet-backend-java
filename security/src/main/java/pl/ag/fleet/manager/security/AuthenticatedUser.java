package pl.ag.fleet.manager.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticatedUser extends AbstractAuthenticationToken {

  private final PrincipalData principal;
  private final Object credentials;

  public AuthenticatedUser(PrincipalData aPrincipal, Object aCredentials) {
    super(null);
    this.principal = aPrincipal;
    this.credentials = aCredentials;
  }


  @Override
  public Object getCredentials() {
    return this.credentials;
  }


  @Override
  public PrincipalData getPrincipal() {
    return this.principal;
  }

  @Override
  public boolean isAuthenticated() {
    return true;
  }
}
