package pl.ag.fleet.application.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.FuelType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

  private String make;
  private String model;
  private Integer productionYear;
  private FuelType fuelType;
  private String vinNumber;

}
