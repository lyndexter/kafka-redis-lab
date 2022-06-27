package com.iot.lab4interfaces.domain.service.monitor;

import org.springframework.stereotype.Service;

@Service
public class DefaultUploadMonitor implements UploadMonitor {
  @Override
  public boolean checkIfLoad(String key) {
    return false;
  }

  @Override
  public int addRepeat(String key) {
    return 0;
  }

  @Override
  public void startLoading(String key) {
    // default method dont do anything
  }

  @Override
  public void finishLoading(String key) {
    // default method dont do anything
  }
}
