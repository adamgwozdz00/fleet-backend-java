package pl.ag.fleet.application.event;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualDataRequest {

  private Long vehicleId;
  private Long driverId;
  private BigDecimal fuelStateInLiters;
  private BigDecimal kilometersState;
}
