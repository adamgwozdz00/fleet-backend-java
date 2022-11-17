package pl.ag.fleet.application.vehicle.details;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverDetail {

  private long id;
  private String lastName;
  private BigDecimal kilometers;
  private String time;
}
