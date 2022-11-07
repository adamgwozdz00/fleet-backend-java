package pl.ag.fleet.acl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.event.EventDTO;
import pl.ag.fleet.event.SendingService;
import pl.ag.fleet.kafka.Event;
import pl.ag.fleet.kafka.producer.EventProducer;

@Component
@RequiredArgsConstructor
public class SendingServiceAdapter implements SendingService {

  private final EventProducer eventProducer;
  private final ObjectMapper mapper;


  @Override
  public void send(EventDTO event) {
    this.eventProducer.send(new Event(mapper.convertValue(event, Map.class)));
  }
}
