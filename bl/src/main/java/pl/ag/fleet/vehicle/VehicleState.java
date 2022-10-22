package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class VehicleState {
  private DriverId actualDriver;
  private Liters actualFuel;
  private Kilometers actualKilometers;

  static VehicleState initial(){
    return new VehicleState(null,new Liters(BigDecimal.ZERO),new Kilometers(BigDecimal.ZERO));
  }
}
