package pl.ag.fleet.event;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.ag.fleet.common.FuelType;

@Value
@EqualsAndHashCode(callSuper = true)
public class CreateVehicleEventDTO extends EventDTO {

  private String vinNumber;
  private Long companyId;
  private String make;
  private String model;
  private Integer productionYear;
  private FuelType fuelType;

  public CreateVehicleEventDTO(EventType eventType, LocalDateTime eventTime,
      String vinNumber, Long companyId, String make, String model, Integer productionYear,
      FuelType fuelType) {
    super(eventType, eventTime);
    this.vinNumber = vinNumber;
    this.companyId = companyId;
    this.make = make;
    this.model = model;
    this.productionYear = productionYear;
    this.fuelType = fuelType;
  }
}
