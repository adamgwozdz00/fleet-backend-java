package pl.ag.fleet.application.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.FuelType;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequest {

  private String vinNumber;
  private Long companyId;
  private String make;
  private String model;
  private Integer productionYear;
  private FuelType fuelType;
}
