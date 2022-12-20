package pl.ag.fleet.common;

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
public class Liters {

  private BigDecimal liters;
}
