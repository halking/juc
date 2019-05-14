package com.hal.juc.sync;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/14
 */
@Slf4j
public class CountDownLatchDemo {

  private static CountDownLatch latch = new CountDownLatch(2);

  public static void main(String[] args) throws Exception {
    log.info("thread {}, latch count {}", Thread.currentThread().getName(), latch.getCount());

    Thread threadA = new Thread(() -> common(), "threadA");

    Thread threadB = new Thread(() -> common(), "threadB");

    threadA.start();
    threadB.start();

    if (latch.getCount() != 0) {
      latch.await();
    }

    log.info("thread {}, latch count {}", Thread.currentThread().getName(), latch.getCount());
  }

  private static void common() {
    try {
      log.info("start thread {} at timestamp {}", Thread.currentThread().getName(), System.currentTimeMillis());
      Thread.sleep(2000);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    } finally {
      latch.countDown();
    }
  }

}
