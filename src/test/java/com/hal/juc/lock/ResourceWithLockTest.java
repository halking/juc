package com.hal.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ResourceWithLockTest {

  @Test
  public void produceAndConsume() {
    Lock lock = new ReentrantLock();
    Condition productCon = lock.newCondition();
    Condition consumeCon = lock.newCondition();
    ResourceWithLock resourceWithLock = new ResourceWithLock(lock, productCon, consumeCon);

    //producer
    for (int i = 0; i < 10; i++) {
      int a = i;
      new Thread(() -> resourceWithLock.put(Integer.valueOf(a))).start();
    }

    //consumer
    for (int i = 0; i < 5; i++) {
      new Thread(() -> resourceWithLock.take()).start();
    }
  }
}