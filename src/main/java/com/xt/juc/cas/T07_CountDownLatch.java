package com.xt.juc.cas;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 门栓
 */
public class T07_CountDownLatch {
    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 1000; j++) {
                    result += j;
                }
            });
        }
        Arrays.stream(threads).forEach(o -> o.start());
        Arrays.stream(threads).forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("join end");
    }

    private static void usingCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 1000; j++) {
                    result += j;
                }
                countDownLatch.countDown();
            });
        }
        Arrays.stream(threads).forEach(o -> o.start());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("countDownLatch end");
    }
}
