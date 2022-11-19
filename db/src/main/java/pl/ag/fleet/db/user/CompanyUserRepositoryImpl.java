package pl.ag.fleet.db.user;

import lombok.RequiredArgsConstructor;
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
    return companyUserJpa.findById(userId).orElse(null);
  }

  @Override
  public void save(User user) {
    this.companyUserJpa.save(user);
  }
}
