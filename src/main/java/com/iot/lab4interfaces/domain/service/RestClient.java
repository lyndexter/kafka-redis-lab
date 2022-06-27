package com.iot.lab4interfaces.domain.service;

import static com.iot.lab4interfaces.domain.model.ExpenseRequest.EXPENSE_TYPE;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestClient {
  
  private final RestTemplate restTemplate;

  public RestClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<Expense> grepExpensesFromAPI(ExpenseRequest expenseRequest, int offset) {
    URI uri = this.uriForSuffix(expenseRequest.getUrlRequest(), expenseRequest.getRowsPartitionCount(), offset);
    RequestEntity<?> request = new RequestEntity<>(HttpMethod.GET, uri);
    return this.restTemplate.exchange(request, EXPENSE_TYPE).getBody();
  }

  public URI uriForSuffix(String url, int rowCount, int offset) {
    return UriComponentsBuilder.fromUriString(url)
        .query("$limit={limit}")
        .query("$offset={offset}")
        .buildAndExpand(rowCount, offset)
        .toUri();
  }
}
