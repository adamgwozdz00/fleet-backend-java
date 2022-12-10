package pl.ag.fleet.application.driver;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.ag.fleet.driver.DriverHistoryRecord;

@AllArgsConstructor
@NoArgsConstructor
public class DriverHistory {

  public List<DriverHistoryRecord> history;
}
