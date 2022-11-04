package pl.ag.fleet.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class InsuranceEventDTO extends EventDTO {

  private String vehicleId;
  private String insuranceName;
  private LocalDate expirationDate;
  private Double cost;

  public InsuranceEventDTO(EventType eventType, LocalDateTime eventTime, String vehicleId,
      String insuranceName, LocalDate expirationDate, Double cost) {
    super(eventType, eventTime);
    this.vehicleId = vehicleId;
    this.insuranceName = insuranceName;
    this.expirationDate = expirationDate;
    this.cost = cost;
  }
}
