package pl.ag.fleet.application.vehicle;

import java.util.List;
import lombok.Data;
import pl.ag.fleet.vehicle.VehicleRecord;

@Data
//@AllArgsConstructor
public class Vehicles {

  private List<VehicleRecord> vehicles;

  public Vehicles(List<VehicleRecord> vehicles) {
    System.out.println(vehicles.size());
    this.vehicles = vehicles;
  }
}
