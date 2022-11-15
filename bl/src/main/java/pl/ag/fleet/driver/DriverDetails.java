package pl.ag.fleet.driver;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
class DriverDetails {

  private String firstName;
  private String LastName;

}
