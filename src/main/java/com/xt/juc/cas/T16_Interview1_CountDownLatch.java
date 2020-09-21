package com.xt.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 面试题1：实现一个容器，提供两个方法 add、size，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实时监控元素个数，当个数到5个时，给出提示并结束
 */
public class T16_Interview1_CountDownLatch {
    List list = new ArrayList();

    public void add(Object o) {
        this.list.add(o);
    }

    public int size(){
        return this.list.size();
    }

    public static void main(String[] args) {
        T16_Interview1_CountDownLatch t = new T16_Interview1_CountDownLatch();
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        Thread t2 = new Thread(() -> {
            if (t.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            latch2.countDown();
        });
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println(i);
                if (t.size() == 5) {
                    latch.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t1.start();
        t2.start();
    }
}
