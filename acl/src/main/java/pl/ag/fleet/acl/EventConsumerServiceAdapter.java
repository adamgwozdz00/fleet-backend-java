package pl.ag.fleet.acl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.event.EventObserver;
import pl.ag.fleet.event.EventType;
import pl.ag.fleet.kafka.consumer.EventConsumerService;
import pl.ag.fleet.kafka.consumer.EventDTO;

@Component
@RequiredArgsConstructor
public class EventConsumerServiceAdapter implements EventConsumerService {

  private final EventObserver eventObserver;

  @Override
  public void process(EventDTO event) {
    eventObserver.observe(
        new pl.ag.fleet.event.EventDTO(EventType.toEnum(event.getEventType()), event.getData()));
  }

}
