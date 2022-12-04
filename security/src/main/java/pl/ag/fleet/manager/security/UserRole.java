package pl.ag.fleet.manager.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
  ADMIN("ADMIN"), USER("USER");

  final String roleName;

}
