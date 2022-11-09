package pl.ag.fleet.db.refuel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.refuel.ReFuel;
import pl.ag.fleet.refuel.ReFuelRepository;

@Repository
@RequiredArgsConstructor
public class RefuelRepositoryImpl implements ReFuelRepository {

  private final RefuelJpa jpa;

  @Override
  public void save(ReFuel fuel) {
    jpa.save(fuel);
  }
}
