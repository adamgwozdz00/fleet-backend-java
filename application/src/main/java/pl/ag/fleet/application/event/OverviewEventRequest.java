package pl.ag.fleet.application.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class OverviewEventRequest {

  private String vehicleId;
  private String name;
  private LocalDate expirationDate;
  private BigDecimal cost;
  private String description;
}
