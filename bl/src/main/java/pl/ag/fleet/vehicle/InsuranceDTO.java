package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Value;

@Value
public class InsuranceDTO {

  private String insuranceTitle;
  private LocalDate expirationDate;
  private BigDecimal cost;
}
