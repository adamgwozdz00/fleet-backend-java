package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Embeddable
class VehicleState {

  @AttributeOverride(name = "id", column = @Column(name = "actual_driver_id"))
  private DriverId actualDriver;
  private Liters actualFuel;
  private Kilometers actualKilometers;

  static VehicleState initial() {
    return new VehicleState(null, new Liters(BigDecimal.ZERO), new Kilometers(BigDecimal.ZERO));
  }
}
