package pl.ag.fleet.event;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class Liters {

  private BigDecimal liters;

  public Liters(BigDecimal liters) {
    if (liters.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Liters can't be less than zero.");
    }
    this.liters = liters;
  }
}
