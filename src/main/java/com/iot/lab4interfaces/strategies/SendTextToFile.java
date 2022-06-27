package com.iot.lab4interfaces.strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("file")
public class SendTextToFile implements SendText {

  private static final Logger logger = LoggerFactory.getLogger(SendTextToFile.class);
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final String fileName;

  public SendTextToFile(@Value("${file.name}") String fileName) {
    this.fileName = fileName;
    try {
      new FileWriter(fileName).close();
      logger.info("file has been cleared/created");
    } catch (IOException exception) {
      logger.error(exception.getMessage());
    }
  }

  @Override
  public void sendText(List<Expense> entities, ExpenseRequest request) {
    try (FileWriter fileWriter = new FileWriter(fileName, true)) {
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      for (Expense expense : entities) {
        bufferedWriter.write(objectMapper.writeValueAsString(expense));
        bufferedWriter.write(",\n");
      }
      logger.info("file has been updated");
    } catch (IOException exception) {
      logger.error(exception.getMessage());
    }
  }
}
