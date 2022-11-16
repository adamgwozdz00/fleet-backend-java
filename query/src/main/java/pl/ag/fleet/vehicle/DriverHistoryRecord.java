package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class DriverHistoryRecord {

  private long id;
  private String lastName;
  private BigDecimal kilometers;
  private LocalDateTime time;
}
