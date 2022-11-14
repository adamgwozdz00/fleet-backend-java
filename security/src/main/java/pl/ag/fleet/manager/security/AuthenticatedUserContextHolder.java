package pl.ag.fleet.manager.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserContextHolder {

  public AuthenticatedUser getAuthenticatedUser() {
    return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication();
  }
}
