package pl.ag.fleet.db.refuel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.refuel.ReFuel;

@Repository
public interface RefuelJpa extends JpaRepository<ReFuel, Long> {

  @Override
  ReFuel save(ReFuel entity);
}
