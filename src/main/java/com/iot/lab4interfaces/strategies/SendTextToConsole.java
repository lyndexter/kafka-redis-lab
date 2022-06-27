package com.iot.lab4interfaces.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("console")
public class SendTextToConsole implements SendText {

  private static final Logger logger = LoggerFactory.getLogger(SendTextToConsole.class);
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void sendText(List<Expense> entities, ExpenseRequest request) {
    try {
      logger.info(objectMapper.writeValueAsString(entities));
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
  }
}
