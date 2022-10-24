package pl.ag.fleet.acl;

import org.springframework.stereotype.Component;
import pl.ag.fleet.kafka.consumer.EventConsumerService;
import pl.ag.fleet.kafka.consumer.EventDTO;

@Component
public class EventConsumerServiceAdapter implements EventConsumerService {

  @Override
  public void process(EventDTO event) {
    System.out.println(event);
  }
}
