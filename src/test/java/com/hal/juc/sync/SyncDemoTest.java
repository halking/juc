package com.hal.juc.sync;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SyncDemoTest {

  @Test
  public void unsafeMethod() throws Exception {
    SyncDemo sync = new SyncDemo();
    new Thread(sync, "unsafe_1").start();
    new Thread(sync, "unsafe_2").start();

    Thread.sleep(3000L);
  }

  @Test
  public void safeMethod() throws Exception {
    SyncDemo sync = new SyncDemo();
    new Thread(sync, "safe_1").start();
    new Thread(sync, "safe_2").start();

    Thread.sleep(4000L);
  }

  @Test
  public void safeClassMethod() throws Exception {
    SyncDemo sync = new SyncDemo();
    new Thread(sync,"class_1").start();
    new Thread(sync,"class_2").start();


    Thread.sleep(4000L);
  }

  @Test
  public void methodWithSync() throws Exception {
    SyncDemo sync = new SyncDemo();

    new Thread(sync,"object_1").start();
    new Thread(sync,"object_2").start();

    Thread.sleep(4000L);
  }

}