package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {

  private String insuranceTitle;
  private LocalDate expirationDate;
  private BigDecimal cost;
}
