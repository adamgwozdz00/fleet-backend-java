package pl.ag.fleet.kafka.producer;

import org.springframework.kafka.support.serializer.JsonSerializer;

public class EventSerializer extends JsonSerializer<Event> {

}
