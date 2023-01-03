package pl.ag.fleet.reports;

import java.math.BigDecimal;

public abstract class Cost {

  protected final BigDecimal cost;

  public Cost(BigDecimal cost) {
    if (cost == null) {
      this.cost = BigDecimal.ZERO;
    } else {
      this.cost = cost;
    }
  }

  abstract Cost add(Cost cost);

  public BigDecimal getCost() {
    if (cost == null) {
      return BigDecimal.ZERO;
    }
    return cost;
  }
}
