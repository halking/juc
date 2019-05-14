package com.hal.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/14
 */
@Slf4j
public class SyncDemo implements Runnable {

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    if (threadName.startsWith("unsafe")) {
      unsafeMethod();
    } else if (threadName.startsWith("safe")) {
      safeMethod();
    } else if (threadName.startsWith("class")) {
      safeClassMethod();
    } else if (threadName.startsWith("object")) {
      methodWithSync();
    }
  }

  private void unsafeMethod() {
    try {
      log.info("start unsafeMethod : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
      Thread.sleep(2000);
      log.info("end unsafeMethod : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private void methodWithSync() {
    log.info("methodWithSync : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
    synchronized (this) {
      try {
        log.info("start methodWithSync : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
        Thread.sleep(2000);
        log.info("end methodWithSync : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
      } catch (InterruptedException e) {
        log.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage(), e);
      }
    }
  }

  private synchronized void safeMethod() {
    try {
      log.info("start safeMethod : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
      Thread.sleep(2000);
      log.info("end safeMethod : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private synchronized static void safeClassMethod() {
    try {
      log.info("start safeClassMethod : {}, timestamp: {}", Thread.currentThread().getName(),
          System.currentTimeMillis());
      Thread.sleep(2000);
      log.info("end safeClassMethod : {}, timestamp: {}", Thread.currentThread().getName(), System.currentTimeMillis());
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
