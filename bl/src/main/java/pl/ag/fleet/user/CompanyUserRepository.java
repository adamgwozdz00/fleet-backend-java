package pl.ag.fleet.user;

public interface CompanyUserRepository {

  User load(UserId userId);

  void save(User user);
}
