package com.hal.juc.common;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/16
 */
public abstract class AbstractProducer<T> implements Producer, Runnable {

  private T value;

  @Override
  public void run() {
    try {
      produce(value);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
