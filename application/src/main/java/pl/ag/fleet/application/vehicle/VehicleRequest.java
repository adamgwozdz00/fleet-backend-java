package pl.ag.fleet.application.vehicle;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.FuelType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

  @NotEmpty
  private String make;
  @NotEmpty
  private String model;
  @NotEmpty
  private Integer productionYear;

  @NotEmpty
  private FuelType fuelType;
  @NotEmpty
  private String vinNumber;

}
