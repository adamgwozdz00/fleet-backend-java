package pl.ag.fleet.vehicle.details;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class RefuelRecord {

  private BigDecimal cost;
  private BigDecimal liters;
}
