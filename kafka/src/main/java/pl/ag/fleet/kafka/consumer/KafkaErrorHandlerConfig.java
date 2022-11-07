package pl.ag.fleet.kafka.consumer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaErrorHandlerConfig {

  @Value("${kafka.servers}")
  private String servers;

  @Value("${kafka.events.dlq.topic}")
  private String deadLetterTopic;

  public ProducerFactory<Object, Object> deadLetterProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        servers);
    configProps.put(
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        new JsonSerializer<>());
    configProps.put(
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        new JsonSerializer<>());
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<Object, Object> DeadLetterKafkaTemplate() {
    return new KafkaTemplate<>(deadLetterProducerFactory());
  }

  @Bean
  public DefaultErrorHandler defaultErrorHandler(
      KafkaTemplate<Object, Object> deadLetterKafkaTemplate) {
    var recover = new DeadLetterPublishingRecoverer(deadLetterKafkaTemplate,
        (cr, e) -> new TopicPartition(deadLetterTopic, 0));
    var backOff = new ExponentialBackOffWithMaxRetries(2);
    backOff.setInitialInterval(10);
    backOff.setMultiplier(100);
    return new DefaultErrorHandler(recover, backOff);
  }
}
