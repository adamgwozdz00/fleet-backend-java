package pl.ag.fleet.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.ag.fleet.common.FuelType;
@AllArgsConstructor
@Getter
class VehicleDetails {
  private String make;
  private String model;
  private Integer ProductionYear;
  private FuelType fuelType;
  private String vinNumber;
}
