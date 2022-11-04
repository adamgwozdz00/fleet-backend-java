package pl.ag.fleet.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class OverviewEventDTO extends EventDTO {

  private String vehicleId;
  private LocalDate expirationDate;
  private String overviewName;
  private Double cost;
  private String description;

  public OverviewEventDTO(EventType eventType, LocalDateTime eventTime, String vehicleId,
      LocalDate expirationDate, String overviewName, Double cost, String description) {
    super(eventType, eventTime);
    this.vehicleId = vehicleId;
    this.expirationDate = expirationDate;
    this.overviewName = overviewName;
    this.cost = cost;
    this.description = description;
  }
}
