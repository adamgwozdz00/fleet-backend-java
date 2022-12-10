package pl.ag.fleet.driver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class DriverHistoryRecord {

  private String vehicleId;
  private String vehicleModel;
  private String vehicleMake;
  private BigDecimal kilometers;
  private BigDecimal liters;

  private LocalDateTime time;

}
