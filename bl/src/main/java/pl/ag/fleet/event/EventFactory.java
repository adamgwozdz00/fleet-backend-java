package pl.ag.fleet.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class EventFactory {

  private final static ObjectMapper MAPPER = new ObjectMapper()
      .registerModule(new JavaTimeModule());

  EventDTO createEvent(Event event) {
    return new EventDTO(event.getEventType(), MAPPER.convertValue(event, Map.class));
  }

}
