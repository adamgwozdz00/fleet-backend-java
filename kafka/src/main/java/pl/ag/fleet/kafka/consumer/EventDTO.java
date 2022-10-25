package pl.ag.fleet.kafka.consumer;

import java.util.Map;
import lombok.Value;

@Value
public class EventDTO {

  private String eventType;
  private Map<String, Object> data;
}
