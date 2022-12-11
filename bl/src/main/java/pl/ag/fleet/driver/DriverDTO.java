package pl.ag.fleet.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

  private String firstName;
  private String lastName;
  private int seniorityInYears;
}
