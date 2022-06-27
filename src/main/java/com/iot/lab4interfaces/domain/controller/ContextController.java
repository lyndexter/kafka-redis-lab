package com.iot.lab4interfaces.domain.controller;

import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import com.iot.lab4interfaces.domain.service.SendingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContextController {
  private final SendingDataService service;

  @Autowired
  public ContextController(SendingDataService service) {
    this.service = service;
  }

  @PostMapping("/")
  public ResponseEntity<String> sendData(@RequestBody ExpenseRequest request) {
    service.processExtApiData(request);
    return ResponseEntity.status(HttpStatus.OK).body("data  was successfully sent");
  }
}
