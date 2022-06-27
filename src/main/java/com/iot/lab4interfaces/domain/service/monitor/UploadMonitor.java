package com.iot.lab4interfaces.domain.service.monitor;

public interface UploadMonitor {

  boolean checkIfLoad(String key);

  int addRepeat(String key);

  void startLoading(String key);

  void finishLoading(String key);
}
