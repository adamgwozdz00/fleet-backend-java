package pl.ag.fleet.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.ag.fleet.kafka.Event;


@Component
@RequiredArgsConstructor
public class EventProducer {

  private final KafkaTemplate<String, Event> template;

  @Value("${kafka.events.topic}")
  private String topic;

  public void send(Event event) {
    template.send(topic, event);
  }
}
