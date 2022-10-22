package pl.ag.fleet.event;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class Kilometers {

  private BigDecimal kilometers;

  public Kilometers(BigDecimal kilometers) {
    if (kilometers.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Kilometers can't be less than zero.");
    }
    this.kilometers = kilometers;
  }
}
