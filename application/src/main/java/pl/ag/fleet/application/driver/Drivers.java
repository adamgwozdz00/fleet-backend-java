package pl.ag.fleet.application.driver;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.driver.DriverRecord;

@Data
@AllArgsConstructor
public class Drivers {

  private List<DriverRecord> drivers;
}
