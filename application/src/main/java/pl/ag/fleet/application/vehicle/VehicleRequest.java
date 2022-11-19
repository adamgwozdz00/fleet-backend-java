package pl.ag.fleet.application.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.common.FuelType;

@Data
@AllArgsConstructor
public class VehicleRequest {

  private String make;
  private String model;
  private Integer productionYear;
  private FuelType fuelType;
  private String vinNumber;

}
