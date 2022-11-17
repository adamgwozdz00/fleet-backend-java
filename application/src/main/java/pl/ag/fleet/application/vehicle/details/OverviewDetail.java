package pl.ag.fleet.application.vehicle.details;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverviewDetail {

  private long id;
  private String overviewName;
  private BigDecimal overviewCost;
  private String expirationDate;
  private String description;
}
