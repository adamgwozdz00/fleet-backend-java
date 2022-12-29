package pl.ag.fleet.application.vehicle.details;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.vehicle.details.RefuelRecord;

@Data
@AllArgsConstructor
public class RefuelDetails {

  private List<RefuelRecord> refuelDetails;
}
