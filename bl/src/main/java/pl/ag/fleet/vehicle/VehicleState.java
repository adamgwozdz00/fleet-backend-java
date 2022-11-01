package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
class VehicleState {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @AttributeOverride(name = "id", column = @Column(name = "actual_driver_id"))
  private DriverId actualDriver;
  private Liters actualFuel;
  private Kilometers actualKilometers;
  @Embedded
  private VehicleId vehicleId;

  public VehicleState(DriverId actualDriver, Liters actualFuel, Kilometers actualKilometers,
      VehicleId vehicleId) {
    this.actualDriver = actualDriver;
    this.actualFuel = actualFuel;
    this.actualKilometers = actualKilometers;
    this.vehicleId = vehicleId;
  }

  static VehicleState initial(VehicleId vehicleId) {
    return new VehicleState(null, new Liters(BigDecimal.ZERO), new Kilometers(BigDecimal.ZERO),
        vehicleId);
  }
}
