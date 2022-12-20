package pl.ag.fleet.db.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ag.fleet.common.VehicleId;
import pl.ag.fleet.vehicle.Vehicle;

@Repository
public interface VehicleJpa extends JpaRepository<Vehicle, VehicleId> {

  @Override
  Vehicle save(Vehicle entity);
}
