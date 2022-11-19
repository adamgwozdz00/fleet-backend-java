package pl.ag.fleet.application.vehicle;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.vehicle.VehicleRecord;

@Data
@AllArgsConstructor
public class Vehicles {

  private List<VehicleRecord> vehicles;
}
