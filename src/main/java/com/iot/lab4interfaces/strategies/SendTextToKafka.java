package com.iot.lab4interfaces.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("kafka")
public class SendTextToKafka implements SendText {

  private static final Logger logger = LoggerFactory.getLogger(SendTextToKafka.class);
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final KafkaTemplate<String, String> kafkaTemplate;
  @Value("${kafka.topic_name}") private String kafkaTopicName;

  public SendTextToKafka(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void sendText(List<Expense> entities, ExpenseRequest request) {
    try {
      kafkaTemplate.send(kafkaTopicName, objectMapper.writeValueAsString(entities));
      logger.info("message is sent");
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
  }
}
