package pl.ag.fleet.reports;

import java.math.BigDecimal;


public class TotalCostRecord extends Cost {

  public TotalCostRecord(BigDecimal cost) {
    super(cost);
  }

  @Override
  public Cost add(Cost cost) {
    return new TotalCostRecord(this.cost.add(cost.getCost()));
  }
}
