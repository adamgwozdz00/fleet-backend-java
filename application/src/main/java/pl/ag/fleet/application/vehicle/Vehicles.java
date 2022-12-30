package pl.ag.fleet.application.vehicle;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ag.fleet.vehicle.VehicleRecord;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicles {

  private List<VehicleRecord> vehicles;

}
