package pl.ag.fleet.application.vehicle.details;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsuranceDetail {

  private long id;
  private String insuranceName;
  private BigDecimal insuranceCost;
  private String insuranceExpirationDate;
}
