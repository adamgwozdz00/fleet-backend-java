package pl.ag.fleet.db.auth;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.auth.AuthUser;
import pl.ag.fleet.auth.AuthUserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserRepositoryImpl implements AuthUserRepository {

  private final AuthUserJpaRepo authJpaRepo;


  @Override
  public Optional<AuthUser> load(long id) {
    return this.authJpaRepo.findById(id);
  }

  @Override
  public Optional<AuthUser> load(String username) {
    return this.authJpaRepo.findByUsername(username);
  }

  @Override
  public AuthUser save(AuthUser authUser) {
    return authJpaRepo.save(authUser);
  }
}
