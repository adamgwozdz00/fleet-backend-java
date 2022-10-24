package pl.ag.fleet.kafka.producer;

import org.springframework.kafka.support.serializer.JsonSerializer;
import pl.ag.fleet.kafka.shared.Event;

public class EventSerializer extends JsonSerializer<Event> {

}
