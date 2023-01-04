package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class TotalCost {

  private BigDecimal cost;

  TotalCost add(CostElement costElement) {
    if (costElement.getCost() != null) {
      return new TotalCost(cost.add(costElement.getCost()));
    }
    return this;
  }
}
