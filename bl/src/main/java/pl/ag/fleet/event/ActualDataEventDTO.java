package pl.ag.fleet.event;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ActualDataEventDTO extends EventDTO {

  private String vehicleId;
  private Long driverId;
  private Double liters;
  private Double kilometers;

  public ActualDataEventDTO(EventType eventType, LocalDateTime eventTime, String vehicleId,
      Long driverId, Double liters, Double kilometers) {
    super(eventType, eventTime);
    this.vehicleId = vehicleId;
    this.driverId = driverId;
    this.liters = liters;
    this.kilometers = kilometers;
  }
}
