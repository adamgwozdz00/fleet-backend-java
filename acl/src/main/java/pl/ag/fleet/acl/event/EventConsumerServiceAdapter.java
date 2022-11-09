package pl.ag.fleet.acl.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.event.EventObserver;
import pl.ag.fleet.event.EventType;
import pl.ag.fleet.event.ObservableEvent;
import pl.ag.fleet.kafka.Event;
import pl.ag.fleet.kafka.consumer.EventConsumerService;

@Component
@RequiredArgsConstructor
public class EventConsumerServiceAdapter implements EventConsumerService {

  private final EventObserver eventObserver;

  @Override
  public void process(Event event) {
    EventType eventType = EventType.valueOf(
        ((String) event.getData().get("eventType")).toUpperCase());

    eventObserver.observe(new ObservableEvent(eventType, event.getData()));
  }

}
