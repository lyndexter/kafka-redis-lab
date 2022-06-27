package com.iot.lab4interfaces.domain.service.monitor;

import java.util.Objects;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UploadRedisMonitor implements UploadMonitor {

  private static final String KEY_REPEATED_PREFIX = "Reapeted-";
  private StringRedisTemplate redisTemplate;

  public UploadRedisMonitor(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public boolean checkIfLoad(String key) {
    var ops = redisTemplate.opsForValue();
    return Boolean.TRUE.equals(redisTemplate.hasKey(key)) && Objects.equals(ops.get(key), "Loaded");
  }

  @Override
  public int addRepeat(String key) {
    var ops = redisTemplate.opsForValue();
    if (!Boolean.TRUE.equals(redisTemplate.hasKey(key)) || ops.get(KEY_REPEATED_PREFIX + key) == null) {
      ops.set(KEY_REPEATED_PREFIX + key, "1");
      return 1;
    }
    int repeatedCount = Integer.parseInt(Objects.requireNonNull(ops.get(KEY_REPEATED_PREFIX + key)));
    ops.set(KEY_REPEATED_PREFIX + key, String.valueOf(++repeatedCount));
    return repeatedCount;
  }

  @Override
  public void startLoading(String key) {
    var ops = redisTemplate.opsForValue();
    ops.set(key, "Loading");
  }

  @Override
  public void finishLoading(String key) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    ops.set(key, "Loaded");
  }
}
