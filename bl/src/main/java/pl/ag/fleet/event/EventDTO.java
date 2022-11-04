package pl.ag.fleet.event;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
@Getter
public abstract class EventDTO {

  private final EventType eventType;
  private final LocalDateTime eventTime;

  public EventDTO(EventType eventType, LocalDateTime eventTime) {
    this.eventType = eventType;
    this.eventTime = eventTime;
  }
}
