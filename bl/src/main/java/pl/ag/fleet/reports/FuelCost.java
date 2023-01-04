package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class FuelCost implements CostElement {

  private BigDecimal cost;

  public static FuelCost of(BigDecimal cost) {
    return new FuelCost(cost);
  }
}
