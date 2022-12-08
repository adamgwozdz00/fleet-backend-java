package pl.ag.fleet.db.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.user.User;

@Repository
public interface CompanyUserJpa extends JpaRepository<User, Long> {

}
