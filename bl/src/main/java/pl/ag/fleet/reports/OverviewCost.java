package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class OverviewCost implements CostElement {

  private BigDecimal cost;

  public static OverviewCost of(BigDecimal cost) {
    return new OverviewCost(cost);
  }
}
