package pl.ag.fleet.application.vehicle.details;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.vehicle.details.DriverHistoryRecord;

@Data
@AllArgsConstructor
public class DriverDetails {

  private List<DriverHistoryRecord> driverDetails;
}
