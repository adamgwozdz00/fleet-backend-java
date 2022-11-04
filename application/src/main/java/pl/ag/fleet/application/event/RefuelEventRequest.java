package pl.ag.fleet.application.event;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RefuelEventRequest {

  private String vehicleId;
  private BigDecimal liters;
  private BigDecimal cost;
}
