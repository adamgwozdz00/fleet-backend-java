package pl.ag.fleet.refuel;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.Liters;
import pl.ag.fleet.common.VehicleId;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(schema = "fleet")
public class ReFuel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Embedded
  private VehicleId vehicleId;
  @Embedded
  private Liters liters;
  @Embedded
  private Cost cost;

  private LocalDateTime time;

  ReFuel(VehicleId vehicleId, Liters liters, Cost cost, LocalDateTime time) {
    this.vehicleId = vehicleId;
    this.liters = liters;
    this.cost = cost;
    this.time = time;
  }
}
