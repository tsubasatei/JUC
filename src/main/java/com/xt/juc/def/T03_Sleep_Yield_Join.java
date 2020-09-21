package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * 线程常用方法：sleep、yield、join
 */
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    private static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("join 1 - " + i);
                // 暂停一会儿线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("join 2 - " + i);
                // 暂停一会儿线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }

    private static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("yield 1 - " + i);
                if (i % 2 == 0) Thread.yield();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("yield 2 - " + i);
                if (i % 2 == 0) Thread.yield();
            }
        }).start();
    }

    private static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("sleep " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
