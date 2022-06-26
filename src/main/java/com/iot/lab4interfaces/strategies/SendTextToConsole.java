package com.iot.lab4interfaces.strategies;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

@Component
public class SendTextToConsole implements SendText {
  @Override
  public void sendText(List<Expense> entities, ExpenseRequest request) {
    for (int rowProcessed = 0; rowProcessed < entities.size(); rowProcessed += request.getRowsPartitionCount()) {
      IntStream.range(rowProcessed, rowProcessed + request.getRowsPartitionCount() >= entities.size()
          ? entities.size() - 1
          : rowProcessed + request.getRowsPartitionCount()).mapToObj(entities::get).forEach(System.out::println);
    }
  }
}
