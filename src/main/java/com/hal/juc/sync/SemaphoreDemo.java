package com.hal.juc.sync;

import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/14
 */
@Slf4j
public class SemaphoreDemo {

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(5);
    for (int i = 0; i < 8; i++) {
      new Thread(new Worker(i, semaphore)).start();
    }
  }

  static class Worker implements Runnable {

    private int num;
    private Semaphore semaphore;

    public Worker(int num, Semaphore semaphore) {
      this.num = num;
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        log.info("Worker-{}占用一个机器...", num);
        Thread.sleep(2000);
        log.info("Worker-{}释放一个机器...", num);
        semaphore.release();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage(), e);
      }
    }
  }
}
