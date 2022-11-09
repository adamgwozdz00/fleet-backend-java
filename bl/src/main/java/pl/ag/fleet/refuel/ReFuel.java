package pl.ag.fleet.refuel;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

  ReFuel(VehicleId vehicleId, Liters liters, Cost cost) {
    this.vehicleId = vehicleId;
    this.liters = liters;
    this.cost = cost;
  }
}
