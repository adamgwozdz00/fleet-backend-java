package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Overview {
  private String name;
  private LocalDate expirationDate;
  private BigDecimal cost;
  private String description;
}
