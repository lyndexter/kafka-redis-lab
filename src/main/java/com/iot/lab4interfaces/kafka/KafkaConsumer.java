package com.iot.lab4interfaces.kafka;

import com.iot.lab4interfaces.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "${kafka.topic_name}", groupId = "${spring.kafka.consumer.group-id}")
  public void listener(String message) {
    logger.info("Received message = {}", message);
  }
}
