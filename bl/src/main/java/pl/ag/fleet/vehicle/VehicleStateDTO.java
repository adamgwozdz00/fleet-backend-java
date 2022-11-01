package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class VehicleStateDTO {

  private Long driverId;
  private BigDecimal liters;
  private BigDecimal kilometers;
}
