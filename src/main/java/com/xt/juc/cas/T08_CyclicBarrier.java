package com.xt.juc.cas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier: 循环栅栏
 */
public class T08_CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满人，发车"));
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
