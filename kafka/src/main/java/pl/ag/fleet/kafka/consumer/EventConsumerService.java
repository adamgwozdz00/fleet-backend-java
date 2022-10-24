package pl.ag.fleet.kafka.consumer;

public interface EventConsumerService {

  void process(EventDTO event);
}
