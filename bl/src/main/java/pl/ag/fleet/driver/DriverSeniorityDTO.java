package pl.ag.fleet.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverSeniorityDTO {

  private Long driverId;
  private Integer seniority;
  private SeniorityOperation operation;
}
