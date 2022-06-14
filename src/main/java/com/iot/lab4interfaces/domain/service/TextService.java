package com.iot.lab4interfaces.domain.service;

import com.iot.lab4interfaces.domain.repository.TextDao;
import com.iot.lab4interfaces.strategies.SendText;
import com.iot.lab4interfaces.strategies.SendTextToConsole;
import com.iot.lab4interfaces.strategies.SendTextToKafka;
import java.io.IOException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class TextService {

  private static final String CONSOLE = "console";
  private static final String KAFKA = "kafka";
  private final TextDao dao;
  private final BeanFactory beanFactory;

  public TextService(TextDao dao, BeanFactory beanFactory) {
    this.dao = dao;
    this.beanFactory = beanFactory;
  }

  public String getTextFromFile() throws IOException {
    return dao.getTextFromFile();
  }

  public boolean sendText(String strategyType) throws IOException {
    String text = getTextFromFile();
    SendText sendTextObj;
    if (strategyType.equals(CONSOLE)) {
      sendTextObj = beanFactory.getBean(SendTextToConsole.class);

    } else if (strategyType.equals(KAFKA)) {
      sendTextObj = beanFactory.getBean(SendTextToKafka.class);
    } else {
      return false;
    }

    sendTextObj.sendText(text);
    return true;
  }
}
