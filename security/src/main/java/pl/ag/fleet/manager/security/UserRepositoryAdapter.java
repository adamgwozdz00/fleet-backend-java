package pl.ag.fleet.manager.security;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.auth.AuthUserRepository;
import pl.ag.fleet.auth.UserRole;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

  private final AuthUserRepository authUserRepository;

  @Override
  public Optional<User> findBy(UserId userId) {
    return authUserRepository.load(userId.getId())
        .map(user -> new User(new UserId(user.getId()),
            new Username(user.getUsername()),
            new Password(user.getPassword()),
            this.toUserRole(user.getRole()),
            new CompanyId(user.getCompanyId())
        ));
  }

  @Override
  public Optional<User> findBy(Username username) {
    return authUserRepository.load(username.getUsername())
        .map(user -> new User(new UserId(user.getId()),
            new Username(user.getUsername()),
            new Password(user.getPassword()),
            this.toUserRole(user.getRole()),
            new CompanyId(user.getCompanyId())
        ));
  }

  private pl.ag.fleet.manager.security.UserRole toUserRole(UserRole role) {
    if (role == UserRole.ADMIN) {
      return pl.ag.fleet.manager.security.UserRole.ADMIN;
    }
    return pl.ag.fleet.manager.security.UserRole.USER;
  }


}
