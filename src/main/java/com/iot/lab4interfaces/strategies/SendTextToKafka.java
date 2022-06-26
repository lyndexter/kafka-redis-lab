package com.iot.lab4interfaces.strategies;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendTextToKafka implements SendText {

  private static final Logger logger = LoggerFactory.getLogger(SendTextToKafka.class);
  private static final String KEY_REPEATED_PREFIX = "Reapeted-";

  private final KafkaTemplate<String, String> kafkaTemplate;
  @Value("${kafka.topic_name}") private String kafkaTopicName;
  private StringRedisTemplate redisTemplate;

  public SendTextToKafka(KafkaTemplate<String, String> kafkaTemplate, StringRedisTemplate redisTemplate) {
    this.kafkaTemplate = kafkaTemplate;
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void sendText(List<Expense> entities, ExpenseRequest request) {

    if (checkIfLoad(request.getUrlRequest())) {
      addRepeat(request.getUrlRequest());
      return;
    }
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    ops.set(request.getUrlRequest(), "Loading");
    for (int rowProcessed = 0; rowProcessed < entities.size(); rowProcessed += request.getRowsPartitionCount()) {
      String message = IntStream.range(rowProcessed, rowProcessed + request.getRowsPartitionCount() >= entities.size()
              ? entities.size() - 1
              : rowProcessed + request.getRowsPartitionCount())
          .mapToObj(entities::get)
          .map(Expense::toString)
          .collect(Collectors.joining());
      kafkaTemplate.send(kafkaTopicName, message);
    }
    ops.set(request.getUrlRequest(), "Loaded");
    logger.info("File has been loaded");
  }

  private boolean checkIfLoad(String key) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    return Boolean.TRUE.equals(redisTemplate.hasKey(key)) && Objects.equals(ops.get(key), "Loaded");
  }

  private void addRepeat(String key) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    if (!Boolean.TRUE.equals(redisTemplate.hasKey(key)) || ops.get(KEY_REPEATED_PREFIX + key) == null) {
      ops.set(KEY_REPEATED_PREFIX + key, "1");
      logger.info("File already has been loaded. Total repeated loads is 1");
      return;
    }

    int repeatedCount = Integer.parseInt(ops.get(KEY_REPEATED_PREFIX + key));
    ops.set(KEY_REPEATED_PREFIX + key, String.valueOf(repeatedCount + 1));
    logger.info("File already has been loaded. Total repeated loads is " + (repeatedCount + 1));

  }
}
