package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class InsuranceCost implements CostElement {

  private BigDecimal cost;

  public static InsuranceCost of(BigDecimal cost) {
    return new InsuranceCost(cost);
  }

}
