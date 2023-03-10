package pl.ag.fleet.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ag.fleet.common.FuelType;

@Data
@AllArgsConstructor
public class VehicleDTO {

  private Long companyId;
  private String make;
  private String model;
  private Integer productionYear;
  private FuelType fuelType;
  private String vinNumber;
}
