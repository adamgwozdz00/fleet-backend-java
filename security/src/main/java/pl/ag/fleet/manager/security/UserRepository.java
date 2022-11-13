package pl.ag.fleet.manager.security;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findBy(UserId userId);

  Optional<User> findBy(Username username);

  User save(User user);
}
