package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleStateDTO {

  private Long driverId;
  private BigDecimal liters;
  private BigDecimal kilometers;
  private LocalDateTime time;

  private VehicleStatus status = VehicleStatus.ACTIVE;

  private BigDecimal longitude;
  private BigDecimal latitude;
}
