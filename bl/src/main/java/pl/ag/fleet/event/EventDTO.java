package pl.ag.fleet.event;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public abstract class EventDTO {

  private final EventType eventType;
  private final LocalDateTime eventTime;

  public EventDTO(EventType eventType, LocalDateTime eventTime) {
    this.eventType = eventType;
    this.eventTime = eventTime;
  }
}
