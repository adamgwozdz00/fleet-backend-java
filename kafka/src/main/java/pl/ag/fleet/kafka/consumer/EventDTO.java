package pl.ag.fleet.kafka.consumer;

import java.util.Map;
import lombok.Value;

@Value
public class EventDTO {

  Map<String, Object> data;
}
