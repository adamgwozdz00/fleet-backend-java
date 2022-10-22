package pl.ag.fleet.application.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class InsuranceEventRequest {

  private Long vehicleId;
  private String name;
  private LocalDate expirationDate;
  private BigDecimal cost;
}
