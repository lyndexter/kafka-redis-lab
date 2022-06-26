package com.iot.lab4interfaces.domain.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.core.ParameterizedTypeReference;

@Data
@ToString
@EqualsAndHashCode
public class ExpenseRequest {

  public static final ParameterizedTypeReference<List<Expense>> EXPENSE_TYPE = new ParameterizedTypeReference<>() {
  };

  private String urlRequest;
  private int rowsPartitionCount;

  public ExpenseRequest(String urlRequest, int rowsPartitionCount) {
    this.urlRequest = urlRequest;
    this.rowsPartitionCount = rowsPartitionCount;
  }

  public String getUrlRequest() {
    return urlRequest;
  }

  public void setUrlRequest(String urlRequest) {
    this.urlRequest = urlRequest;
  }

  public int getRowsPartitionCount() {
    return rowsPartitionCount;
  }

  public void setRowsPartitionCount(int rowsPartitionCount) {
    this.rowsPartitionCount = rowsPartitionCount;
  }

}
