package pl.ag.fleet.event;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class Cost {

  private BigDecimal cost;

  public Cost(BigDecimal cost) {
    if (cost.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Cost can't be less than zero.");
    }
    this.cost = cost;
  }
}
