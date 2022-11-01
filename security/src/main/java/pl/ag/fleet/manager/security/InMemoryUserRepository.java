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

  private final Map<Username, User> userIdUserMap;

  InMemoryUserRepository() {
    this.userIdUserMap = new HashMap<>();
  }

  @Override
  public Optional<User> findByUsername(Username username) {
    return Optional.of(this.userIdUserMap.get(username));
  }

  @Override
  public User save(User user) {
    this.userIdUserMap.put(user.getUsername(), user);
    return user;
  }

  @PostConstruct
  void initAccounts() {
    System.out.println("Post construct fires!");
    val encoder = new BCryptPasswordEncoder();
    this.save(new User(UserId.generate(), new Username("admin@example.com"),
        new Password("f00pass123").encode(encoder), "admin"));
    this.save(new User(UserId.generate(), new Username("user@example.com"),
        new Password("f00pass123").encode(encoder), "user"));
  }
}
