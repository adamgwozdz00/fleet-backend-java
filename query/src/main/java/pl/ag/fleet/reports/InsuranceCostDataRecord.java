package pl.ag.fleet.reports;

import java.math.BigDecimal;


public class InsuranceCostDataRecord extends Cost {

  public InsuranceCostDataRecord(BigDecimal cost) {
    super(cost);
  }

  @Override
  public Cost add(Cost cost) {
    return new InsuranceCostDataRecord(this.cost.add(cost.getCost()));
  }
}
