package com.xt.juc.container;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class T10_LinkedBlockingQueue {
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    static Random r = new Random();
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put("a" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take - " + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }
}
