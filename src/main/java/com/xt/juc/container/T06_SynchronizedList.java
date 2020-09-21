package com.xt.juc.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 买火车票
 */
public class T06_SynchronizedList {
    static List<String> tickets = Collections.synchronizedList(new ArrayList<>());
    static {
        for (int i = 0; i < 100; i++) {
            tickets.add("票号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (tickets) {
                    while (tickets.size() > 0) {
                        // 暂停一会儿线程
                        try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                        System.out.println(Thread.currentThread().getName() + "\t销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
