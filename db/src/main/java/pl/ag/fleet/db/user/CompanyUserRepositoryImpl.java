package pl.ag.fleet.db.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.user.CompanyUserRepository;
import pl.ag.fleet.user.User;
import pl.ag.fleet.user.UserId;

@Repository
@RequiredArgsConstructor
public class CompanyUserRepositoryImpl implements CompanyUserRepository {

  private final CompanyUserJpa companyUserJpa;

  @Override
  public User load(UserId userId) {
    return companyUserJpa.findById(userId.getUserId()).orElse(null);
  }

  @Override
  public void save(User user) {
    this.companyUserJpa.save(user);
  }

  @Override
  public void delete(UserId userId) {
    val user = this.companyUserJpa.findById(userId.getUserId());
    if (user.isEmpty()) {
      throw new IllegalStateException("User not exists");
    }
    this.companyUserJpa.delete(user.get());
  }
}
