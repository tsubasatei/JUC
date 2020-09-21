package com.xt.juc.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T20_InterviewA1B2_BlockingQueue {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    static BlockingQueue bq1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue bq2 = new ArrayBlockingQueue<>(1);
    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (char c : aC) {
                try {
                    System.out.print(c);
                    bq1.put("ok");
                    bq2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2 = new Thread(() -> {
            for (char c : aI) {
                try {
                    bq1.take();
                    System.out.print(c);
                    bq2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
