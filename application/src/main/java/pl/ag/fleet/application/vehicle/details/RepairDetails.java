package pl.ag.fleet.application.vehicle.details;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.vehicle.details.RepairRecord;

@Data
@AllArgsConstructor
public class RepairDetails {

  private List<RepairRecord> repairDetails;
}
