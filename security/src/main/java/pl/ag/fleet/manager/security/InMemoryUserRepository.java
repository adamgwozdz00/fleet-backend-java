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
    this.save(new User(new UserId("8d5dcfdc-aca3-4aa0-bb66-c13eabf0c985"),
        new Username("admin@example.com"),
        new Password("f00pass123").encode(encoder), "admin", new CompanyId(1)));
    this.save(new User(new UserId("af2990c0-0b2b-4f3b-8866-53e30380589a"),
        new Username("user@example.com"),
        new Password("f00pass123").encode(encoder), "user", new CompanyId(1)));

    this.save(new User(new UserId("9c17d473-f182-4398-8df8-907cc552496f"),
        new Username("user.company2@example.com"),
        new Password("f00pass123").encode(encoder), "user", new CompanyId(2)));

    this.save(new User(new UserId("ca89ca40-a445-43a6-b7e7-9ea98027cc84"),
        new Username("admin.company3@example.com"),
        new Password("f00pass123").encode(encoder), "admin", new CompanyId(3)));
  }

}

