package pl.ag.fleet.application.vehicle.details;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.vehicle.details.VehicleStateRecord;

@Data
@AllArgsConstructor
public class VehicleStateDetails {

  private List<VehicleStateRecord> vehicleStateDetails;
}
