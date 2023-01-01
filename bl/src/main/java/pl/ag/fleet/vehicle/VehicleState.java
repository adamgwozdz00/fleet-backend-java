package pl.ag.fleet.vehicle;

import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.Liters;
import pl.ag.fleet.common.VehicleId;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(schema = "fleet")
class VehicleState {

  @Enumerated(EnumType.STRING)
  VehicleStatus status;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @AttributeOverride(name = "id", column = @Column(name = "actual_driver_id"))
  private DriverId actualDriver;
  private Liters actualFuel;
  private Kilometers actualKilometers;
  private LocalDateTime time;
  @Embedded
  private VehicleId vehicleId;


  public VehicleState(DriverId actualDriver, Liters actualFuel, Kilometers actualKilometers,
      LocalDateTime time, VehicleId vehicleId) {
    this(actualDriver, actualFuel, actualKilometers, time, vehicleId, VehicleStatus.ACTIVE);
  }

  public VehicleState(DriverId actualDriver, Liters actualFuel, Kilometers actualKilometers,
      LocalDateTime time, VehicleId vehicleId, VehicleStatus status) {
    this.actualDriver = actualDriver;
    this.actualFuel = actualFuel;
    this.actualKilometers = actualKilometers;
    this.time = time;
    this.vehicleId = vehicleId;
    this.status = status;
  }

}
