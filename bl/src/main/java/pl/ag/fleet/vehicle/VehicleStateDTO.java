package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleStateDTO {

  private Long driverId;
  private BigDecimal liters;
  private BigDecimal kilometers;
  private LocalDateTime time;
}
