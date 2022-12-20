package pl.ag.fleet.auth;

import java.util.Optional;

public interface AuthUserRepository {

  Optional<AuthUser> load(long id);

  Optional<AuthUser> load(String username);

  AuthUser save(AuthUser authUser);

  void delete(Long userId);
}
