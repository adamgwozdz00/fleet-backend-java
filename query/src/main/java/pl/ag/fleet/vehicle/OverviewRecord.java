package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Value;

@Value
public class OverviewRecord {

  private long id;
  private BigDecimal overviewCost;
  private LocalDate overviewExpirationDate;
  private String overviewName;
  private String overviewDescription;
}
