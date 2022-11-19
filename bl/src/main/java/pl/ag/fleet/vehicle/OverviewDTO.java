package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverviewDTO {

  private String overviewTitle;
  private LocalDate expirationDate;
  private BigDecimal cost;
  private String description;
}
