package com.iot.lab4interfaces.domain.service;

import com.iot.lab4interfaces.domain.model.Expense;
import com.iot.lab4interfaces.domain.model.ExpenseRequest;
import com.iot.lab4interfaces.domain.service.monitor.DefaultUploadMonitor;
import com.iot.lab4interfaces.domain.service.monitor.UploadMonitor;
import com.iot.lab4interfaces.domain.service.monitor.UploadRedisMonitor;
import com.iot.lab4interfaces.strategies.SendText;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendingDataService {

  private static final Logger logger = LoggerFactory.getLogger(SendingDataService.class);
  private final RestClient restClient;
  private SendText sendTextObj;
  private UploadMonitor uploadMonitor;

  public SendingDataService(BeanFactory beanFactory, RestClient restClient, @Value("${strategy}") String strategyType) {
    this.restClient = restClient;

    try {
      sendTextObj = (SendText) beanFactory.getBean(strategyType);
      if (strategyType.equals("kafka")) {
        uploadMonitor = beanFactory.getBean(UploadRedisMonitor.class);
      } else {
        uploadMonitor = beanFactory.getBean(DefaultUploadMonitor.class);
      }
    } catch (BeansException exception) {
      logger.error("data using " + strategyType + " strategy was unsuccessfully sent");
      throw exception;
    }
  }

  public void processExtApiData(ExpenseRequest request) {
    int offset = 0;
    int uploadCount = uploadMonitor.checkIfLoad(request.getUrlRequest()) ? uploadMonitor.addRepeat(
        request.getUrlRequest()) : 0;
    logger.info(String.format("File has been loaded %s times", uploadCount));
    if (uploadCount >= 1) {
      return;
    }
    uploadMonitor.startLoading(request.getUrlRequest());
    logger.info("Started loading");
    while (true) {
      List<Expense> expenseList = restClient.grepExpensesFromAPI(request, offset);
      offset += request.getRowsPartitionCount();

      if (expenseList == null || expenseList.isEmpty()) {
        uploadMonitor.finishLoading(request.getUrlRequest());
        logger.info("Finish loading");
        return;
      }
      sendTextObj.sendText(expenseList, request);
    }
  }
}
