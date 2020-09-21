package com.xt.juc.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 买火车票：ArrayList chaomai
 */
public class T05_ArrayList {
    static List<String> tickets = new ArrayList<>();
    static {
        for (int i = 0; i < 10; i++) {
            tickets.add("票号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() == 0) break;
                        // 暂停一会儿线程
                        try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                        System.out.println(Thread.currentThread().getName() + "\t销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
