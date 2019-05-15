package com.hal.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/15
 */
public class LockDemo {

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    Condition productCon = lock.newCondition();
    Condition consumeCon = lock.newCondition();
    ResourceQueue resourceQueue = new ResourceQueue(lock, productCon, consumeCon);

    //producer
    for (int i = 0; i < 10; i++) {
      int a = i;
      new Thread(() -> resourceQueue.put(Integer.valueOf(a))).start();
    }

    //consumer
    for (int i = 0; i < 5; i++) {
      new Thread(() -> resourceQueue.take()).start();
    }

  }
}
