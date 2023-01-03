package pl.ag.fleet.reports;

import java.math.BigDecimal;


public class OverviewCostDataRecord extends Cost {

  public OverviewCostDataRecord(BigDecimal cost) {
    super(cost);
  }

  @Override
  public Cost add(Cost cost) {
    return new OverviewCostDataRecord(this.cost.add(cost.getCost()));
  }
}
