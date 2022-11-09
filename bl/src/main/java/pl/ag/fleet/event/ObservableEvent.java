package pl.ag.fleet.event;

import java.util.Map;
import lombok.Value;

@Value
public class ObservableEvent {

  private EventType eventType;
  private Map<String, Object> data;
}
