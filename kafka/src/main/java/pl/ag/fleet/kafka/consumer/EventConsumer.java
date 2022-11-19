package pl.ag.fleet.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import pl.ag.fleet.kafka.Event;

@Component
@RequiredArgsConstructor
public class EventConsumer {

  private final EventConsumerService service;

  //  @KafkaListener(topics = "${kafka.events.topic}")
  void listen(ConsumerRecord<String, Event> record) {
    this.service.process(record.value());
  }

}
