package com.iot.lab4interfaces.strategies;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendTextToFile implements SendText {

  private static final Logger logger = LoggerFactory.getLogger(SendTextToFile.class);

  @Value("${file.name}") private String fileName;

  @Override
  public void sendText(List<Expense> entities, ExpenseRequest request) {
    try {
      FileWriter fileWriter = new FileWriter(fileName);
      List<String> expensesStringsList = entities.stream().map(Expense::toString).collect(Collectors.toList());
      for (String expensesString : expensesStringsList) {
        fileWriter.write(expensesString);
      }
      logger.info("file has been created");
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
