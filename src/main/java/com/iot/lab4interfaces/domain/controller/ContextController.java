package com.iot.lab4interfaces.domain.controller;

import com.iot.lab4interfaces.domain.service.TextService;
import java.io.IOException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContextController {
  private final TextService service;
  @Value("${strategy}") private String strategyType;

  @Autowired
  public ContextController(TextService service) {
    this.service = service;
  }

  @PostMapping("/")
  public ResponseEntity<String> sendData() throws IOException {
    if (service.sendText(strategyType)) {
      return ResponseEntity.status(HttpStatus.OK)
          .body("data using " + strategyType + " strategy was successfully sent");
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("data using " + strategyType + " strategy was unsuccessfully " + "sent");
  }
}
