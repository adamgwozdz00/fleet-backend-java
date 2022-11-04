package pl.ag.fleet.kafka.consumer;

import pl.ag.fleet.kafka.Event;

public interface EventConsumerService {

  void process(Event event);
}
