package com.xt.juc.container;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 买火车票
 * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
 * Vector每个方法加锁，但是连续调用几个方法没有枷锁
 */
public class T04_Vector {
    static Vector<String> tickets = new Vector<>();
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
