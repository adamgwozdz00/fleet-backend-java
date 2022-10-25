package pl.ag.fleet.kafka.producer;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Event {

  private String eventType;
  private Map<String, Object> data;
}
