package pl.ag.fleet.vehicle;

import lombok.Value;
import pl.ag.fleet.common.FuelType;

@Value
public class VehicleDTO {

  private Long companyId;
  private String make;
  private String model;
  private Integer productionYear;
  private FuelType fuelType;
  private String vinNumber;
}
