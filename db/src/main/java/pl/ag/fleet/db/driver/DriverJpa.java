package pl.ag.fleet.db.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.driver.Driver;

@Repository
public interface DriverJpa extends JpaRepository<Driver, Long> {

  @Override
  Driver save(Driver driver);
}
