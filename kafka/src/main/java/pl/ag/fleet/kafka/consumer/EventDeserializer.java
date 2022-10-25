package pl.ag.fleet.kafka.consumer;


import org.springframework.kafka.support.serializer.JsonDeserializer;
import pl.ag.fleet.kafka.producer.Event;

public class EventDeserializer extends JsonDeserializer<Event> {


}
