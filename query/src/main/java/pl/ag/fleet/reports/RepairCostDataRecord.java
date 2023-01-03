package pl.ag.fleet.reports;

import java.math.BigDecimal;


public class RepairCostDataRecord extends Cost {

  public RepairCostDataRecord(BigDecimal cost) {
    super(cost);
  }

  @Override
  public Cost add(Cost cost) {
    return new RepairCostDataRecord(this.cost.add(cost.getCost()));
  }
}
