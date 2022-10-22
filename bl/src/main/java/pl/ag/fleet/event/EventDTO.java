package pl.ag.fleet.event;

import java.util.Map;
import lombok.Value;

@Value
public class EventDTO {

  private Map<String, Object> data;
}
