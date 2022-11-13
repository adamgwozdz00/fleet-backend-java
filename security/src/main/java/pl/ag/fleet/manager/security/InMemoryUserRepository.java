package pl.ag.fleet.manager.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryUserRepository implements UserRepository {

  private final Map<UserId, User> userIdUserMap;

  InMemoryUserRepository() {
    this.userIdUserMap = new HashMap<>();
  }

  @Override
  public Optional<User> findBy(UserId userId) {
    return Optional.of(this.userIdUserMap.get(userId));
  }

  @Override
  public Optional<User> findBy(Username username) {
    return this.userIdUserMap.values().stream().filter(user -> user.getUsername().equals(username))
        .findFirst();
  }

  @Override
  public User save(User user) {
    this.userIdUserMap.put(user.getUserId(), user);
    return user;
  }

  @PostConstruct
  void initAccounts() {
    System.out.println("Post construct fires!");
    val encoder = new BCryptPasswordEncoder();
    this.save(new User(UserId.generate(), new Username("admin@example.com"),
        new Password("f00pass123").encode(encoder), "admin", new CompanyId(1)));
    this.save(new User(UserId.generate(), new Username("user@example.com"),
        new Password("f00pass123").encode(encoder), "user", new CompanyId(1)));
  }
}
