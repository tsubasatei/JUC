package com.xt.juc.pc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
    public static void main(String[] args) {
        BlockingQueue<Goods> blockingQueue = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer, "生产者").start();
        new Thread(consumer, "消费者").start();
    }
}
