package pl.ag.fleet.refuel;

import java.math.BigDecimal;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
class Cost {

  private BigDecimal cost;
}
