package pl.ag.fleet.refuel;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class RefuelDTO {

  private VehicleId vehicleId;
  private BigDecimal liters;
  private BigDecimal cost;

}
