package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class RepairCost implements CostElement {

  private BigDecimal cost;

  public static RepairCost of(BigDecimal cost) {
    return new RepairCost(cost);
  }
}
