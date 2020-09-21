package com.xt.juc.container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * 买火车票
 */
public class T07_Queue {
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();
    static {
        for (int i = 0; i < 10; i++) {
            tickets.add("票号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
                    String poll = tickets.poll();
                    try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                    if (poll == null) break;
                    else System.out.println(Thread.currentThread().getName() + "\t销售了--" + poll);
                }

            }).start();
        }
    }
}
