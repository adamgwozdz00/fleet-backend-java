package pl.ag.fleet.kafka.producer;

import java.util.Map;
import lombok.Value;

@Value
public class Event {

  private Map<String, Object> data;
}
