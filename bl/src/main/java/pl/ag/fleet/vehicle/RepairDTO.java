package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {

  private LocalDateTime from;
  private LocalDateTime to;
  private String serviceName;
  private BigDecimal cost;
  private String title;
}
