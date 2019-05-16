package com.hal.juc.queue;

import com.google.common.collect.Queues;
import java.util.concurrent.BlockingQueue;
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
public class ResourceWithQueueTest {

  @Test
  public void produceAndConsume() {
    BlockingQueue queue = Queues.newArrayBlockingQueue(5);
    ResourceWithQueue resourceWithQueue = new ResourceWithQueue(queue);

    for (int i = 0; i < 10; i++) {
      Integer value = Integer.valueOf(i);
      new Thread(() -> resourceWithQueue.put(value)).start();
    }

    //consumer
    for (int i = 0; i < 5; i++) {
      new Thread(() -> resourceWithQueue.take()).start();
    }
  }
}