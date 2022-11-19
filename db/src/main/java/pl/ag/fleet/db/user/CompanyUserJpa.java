package pl.ag.fleet.db.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.user.User;
import pl.ag.fleet.user.UserId;

@Repository
public interface CompanyUserJpa extends JpaRepository<User, UserId> {

  @Override
  Optional<User> findById(UserId userId);
}
