package com.hal.juc.common;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/16
 */
public interface Producer<T> {

  void produce(T value);
}
