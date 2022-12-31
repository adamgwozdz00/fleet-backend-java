package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class OverviewCostDataRecord implements Cost {

  private BigDecimal cost;

  @Override
  public Cost add(Cost cost) {
    return new OverviewCostDataRecord(this.cost.add(cost.getCost()));
  }
}
