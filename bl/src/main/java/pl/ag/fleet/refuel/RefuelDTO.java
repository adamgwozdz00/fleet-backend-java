package pl.ag.fleet.refuel;

import java.math.BigDecimal;
import lombok.Value;
import pl.ag.fleet.common.VehicleId;

@Value
public class RefuelDTO {

  private VehicleId vehicleId;
  private BigDecimal liters;
  private BigDecimal cost;

}
