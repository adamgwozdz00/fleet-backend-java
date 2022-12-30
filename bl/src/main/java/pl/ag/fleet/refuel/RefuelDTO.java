package pl.ag.fleet.refuel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefuelDTO {

  private BigDecimal liters;
  private BigDecimal cost;
  private LocalDateTime time;

}
