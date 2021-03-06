package com.hal.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/15
 */
@Slf4j
public class ResourceWithLock {

  private List<Integer> resources = new ArrayList<>();
  private int num = 0, size = 5;
  private AtomicInteger count = new AtomicInteger(0);
  private Lock lock;
  private Condition productCon;
  private Condition consumeCon;

  public ResourceWithLock(Lock lock, Condition productCon, Condition consumeCon) {
    this.lock = lock;
    this.productCon = productCon;
    this.consumeCon = consumeCon;
  }

  public void put(Integer value) {
    lock.lock();
    try {
      while (size == resources.size()) {
        log.info("队列已满,产品[{}]等待生产...", value);
        productCon.await();
      }

      resources.add(value);
      count.incrementAndGet();
      log.info("队列不满,生产[{}]", value);

      consumeCon.signal();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    } finally {
      lock.unlock();
    }
  }

  public Integer take() {
    lock.lock();
    try {
      while (count.get() <= num) {
        log.info("队列已空,count[{}]", count);
        consumeCon.await();
      }

      Integer result = resources.remove(count.get() - 1);
      count.decrementAndGet();
      log.info("队列不空,消费[{}]", result);

      productCon.signal();
      return result;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    } finally {
      lock.unlock();
    }
  }
}
