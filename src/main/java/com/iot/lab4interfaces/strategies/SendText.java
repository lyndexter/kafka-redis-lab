package com.iot.lab4interfaces.strategies;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.util.List;

public interface SendText {
  void sendText(List<Expense> entities, ExpenseRequest request);
}
