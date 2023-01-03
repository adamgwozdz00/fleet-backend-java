package pl.ag.fleet.reports;

import java.math.BigDecimal;

public class RefuelCostDataRecord extends Cost {

  public RefuelCostDataRecord(BigDecimal cost) {
    super(cost);
  }

  @Override
  public Cost add(Cost cost) {
    return new RefuelCostDataRecord(this.cost.add(cost.getCost()));
  }


}
