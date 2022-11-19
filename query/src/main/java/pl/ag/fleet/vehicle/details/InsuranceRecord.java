package pl.ag.fleet.vehicle.details;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Value;

@Value
public class InsuranceRecord {

  private long id;
  private BigDecimal insuranceCost;
  private LocalDate insuranceExpirationDate;
  private String insuranceName;
}
