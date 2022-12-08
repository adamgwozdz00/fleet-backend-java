package pl.ag.fleet.db.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.auth.AuthUser;

@Repository
public interface AuthUserJpaRepo extends JpaRepository<AuthUser, Long> {

  Optional<AuthUser> findByUsername(String username);
}
