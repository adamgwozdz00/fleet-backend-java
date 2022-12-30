package pl.ag.fleet.vehicle.details;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class RefuelRecord {

  private BigDecimal cost;
  private BigDecimal liters;
  private LocalDateTime time;
}
