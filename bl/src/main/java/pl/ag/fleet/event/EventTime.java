package pl.ag.fleet.event;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class EventTime {

  private LocalDateTime time;

  EventTime() {
    this.time = LocalDateTime.now();
  }
}
