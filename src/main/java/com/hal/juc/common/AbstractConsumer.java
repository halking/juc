package com.hal.juc.common;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/16
 */
public abstract class AbstractConsumer<T> implements Consumer, Runnable {

  @Override
  public void run() {
    try {
      consume();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
