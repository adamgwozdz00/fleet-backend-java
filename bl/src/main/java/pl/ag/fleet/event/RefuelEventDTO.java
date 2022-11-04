package pl.ag.fleet.event;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RefuelEventDTO extends EventDTO {

  private String vehicleId;
  private Double liters;
  private Double cost;

  public RefuelEventDTO(EventType eventType, LocalDateTime eventTime, String vehicleId,
      Double liters, Double cost) {
    super(eventType, eventTime);
    this.vehicleId = vehicleId;
    this.liters = liters;
    this.cost = cost;
  }
}
