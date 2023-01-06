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

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(schema = "fleet")
class VehicleState implements Comparable<VehicleState> {

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
  private Position position;

  public VehicleState(DriverId actualDriver, Liters actualFuel, Kilometers actualKilometers,
      LocalDateTime time, VehicleStatus status,
      Position position) {
    this.actualDriver = actualDriver;
    this.actualFuel = actualFuel;
    this.actualKilometers = actualKilometers;
    this.time = time;
    this.status = status;
    this.position = position;
  }

  boolean hasAnyDriverAt(VehicleState state) {
    return this.time.toLocalDate().equals(state.time.toLocalDate());
  }

  boolean isDriverAvailableAt(DriverAvailabilityService driverAvailabilityService) {
    return driverAvailabilityService.isAvailableAt(this.actualDriver, this.time);
  }


  @Override
  public int compareTo(VehicleState o) {
    return time.compareTo(o.time);
  }

  public boolean isValidNewNumberOfKilometers(VehicleState state) {
    return this.actualKilometers.isLessOrEqualThan(state.actualKilometers);
  }
}
