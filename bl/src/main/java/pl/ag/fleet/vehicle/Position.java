package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Position {

  private BigDecimal longitude;
  private BigDecimal latitude;
}
