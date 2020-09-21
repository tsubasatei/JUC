package com.xt.juc.container;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T17_InterviewA1B2_Wait_Notify {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    static final Object o = new Object();
    static volatile boolean flag = true;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    flag = false;
                    System.out.print(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        });

        t2 = new Thread(() -> {
            synchronized (o) {
                while (flag) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : aI) {
                    System.out.print(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        });

        t1.start();
        t2.start();
    }
}
