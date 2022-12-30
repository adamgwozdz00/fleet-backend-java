package pl.ag.fleet.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRecord {

  private long id;
  private String lastName;
  private String firstName;
  private int seniority;
  private String title;
}
