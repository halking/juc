package com.hal.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/16
 */
@Slf4j
public class ResourceWithQueue {

  private BlockingQueue queue;
  private AtomicInteger count = new AtomicInteger(0);

  public ResourceWithQueue(BlockingQueue queue) {
    this.queue = queue;
  }

  public void put(Integer value) {
    try {
      queue.put(value);
      count.incrementAndGet();
      log.info("队列不满,生产[{}]", value);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Integer take() {
    try {
      Integer result = (Integer) queue.take();
      count.decrementAndGet();
      log.info("队列不空,消费[{}]", result);

      return result;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
