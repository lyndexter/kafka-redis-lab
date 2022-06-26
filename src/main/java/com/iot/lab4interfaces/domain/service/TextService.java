package com.iot.lab4interfaces.domain.service;

import static com.iot.lab4interfaces.domain.model.ExpenseRequest.EXPENSE_TYPE;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import com.iot.lab4interfaces.strategies.SendText;
import com.iot.lab4interfaces.strategies.SendTextToConsole;
import com.iot.lab4interfaces.strategies.SendTextToFile;
import com.iot.lab4interfaces.strategies.SendTextToKafka;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TextService {

  private static final String CONSOLE = "console";
  private static final String KAFKA = "kafka";
  private static final String FILE = "file";
  private final BeanFactory beanFactory;

  @Autowired private RestTemplate restTemplate;

  @Value("${strategy}") private String strategyType;

  public TextService(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  public boolean sendText(ExpenseRequest request) throws RuntimeException {
    List<Expense> expenses = grepExpensesFromAPI(request.getUrlRequest());

    SendText sendTextObj;
    if (strategyType.equals(CONSOLE)) {
      sendTextObj = beanFactory.getBean(SendTextToConsole.class);
    } else if (strategyType.equals(KAFKA)) {
      sendTextObj = beanFactory.getBean(SendTextToKafka.class);
    } else if (strategyType.equals(FILE)) {
      sendTextObj = beanFactory.getBean(SendTextToFile.class);
    } else {
      throw new RuntimeException("data using " + strategyType + " strategy was unsuccessfully " + "sent");
    }

    sendTextObj.sendText(expenses, request);
    return true;
  }

  private List<Expense> grepExpensesFromAPI(String url) {
    URI uri = this.uriForSuffix(url);
    RequestEntity<?> request = new RequestEntity(HttpMethod.GET, uri);
    return this.restTemplate.exchange(request, EXPENSE_TYPE).getBody();
  }

  public URI uriForSuffix(String url) {
    return UriComponentsBuilder.fromUriString(url).build().toUri();
  }
}
