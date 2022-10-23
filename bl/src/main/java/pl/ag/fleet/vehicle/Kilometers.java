package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
class Kilometers {

  private BigDecimal kilometers;

  public boolean isLessOrEqualThan(Kilometers kilometers) {
    return this.kilometers.compareTo(kilometers.kilometers) < 1;
  }
}
