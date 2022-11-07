package pl.ag.fleet.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {

//  @Value("${kafka.events.topic}")
  private String eventsTopicName;

//  @Value("${kafka.events.dlq.topic}")
  private String deadLetterTopic;

//  @Value("${kafka.events.topic.partitions}")
  private int numberOfPartitions;
//  @Value("${kafka.events.retention.ms}")
  private long eventsTopicRetention;

//  @Value("${kafka.events.dlq.retention.ms}")
  private long deadLetterTopicRetention;


//  @Bean
  public NewTopic eventsTopic() {
    return TopicBuilder.name(eventsTopicName)
        .partitions(numberOfPartitions)
        .config(TopicConfig.RETENTION_MS_CONFIG, "" + eventsTopicRetention)
        .build();
  }

//  @Bean
  public NewTopic deadLetterTopic() {
    return TopicBuilder.name(deadLetterTopic)
        .partitions(1)
        .config(TopicConfig.RETENTION_MS_CONFIG, "" + deadLetterTopicRetention)
        .build();
  }
}
