package com.hal.juc.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/16
 */
@Slf4j
public class ResourceWithSync {

  private List<Integer> resources = new ArrayList<>();
  private int num = 0, size = 5;
  private AtomicInteger count = new AtomicInteger(0);

  public void put(Integer value) {
    try {
      synchronized (this) {
        while (size == resources.size()) {
          log.info("队列已满,产品[{}]等待生产...", value);
          wait();
        }

        resources.add(value);
        count.incrementAndGet();
        log.info("队列不满,生产[{}]", value);

        notifyAll();
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Integer take() {
    try {
      synchronized (this) {
        while (count.get() <= num) {
          log.info("队列已空,count[{}]", count);
          wait();
        }

        Integer result = resources.remove(count.get() - 1);
        count.decrementAndGet();
        log.info("队列不空,消费[{}]", result);

        notifyAll();
        return result;
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
