package com.xt.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * 面试题1：实现一个容器，提供两个方法 add、size，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实时监控元素个数，当个数到5个时，给出提示并结束
 */
public class T18_Interview1_Semaphore {
    List list = new ArrayList();

    public void add(Object o) {
        this.list.add(o);
    }

    public int size() {
        return this.list.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {
        T18_Interview1_Semaphore t = new T18_Interview1_Semaphore();
        Semaphore semaphore = new Semaphore(1);
        t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("t2 结束");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    t.add(new Object());
                    System.out.println(i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    t.add(new Object());
                    System.out.println(i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
    }
}
