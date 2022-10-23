package pl.ag.fleet.vehicle;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.FuelType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
class VehicleDetails {

  private String make;
  private String model;
  private Integer ProductionYear;
  @Enumerated(EnumType.STRING)
  private FuelType fuelType;
  private String vinNumber;
}
