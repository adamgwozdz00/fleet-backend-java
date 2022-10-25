package pl.ag.fleet.kafka.consumer;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

  private String eventType;
  private Map<String, Object> data;
}
