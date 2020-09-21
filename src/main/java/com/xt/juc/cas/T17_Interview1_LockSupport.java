package com.xt.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 面试题1：实现一个容器，提供两个方法 add、size，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实时监控元素个数，当个数到5个时，给出提示并结束
 */
public class T17_Interview1_LockSupport {
    List list = new ArrayList();

    public void add(Object o) {
        this.list.add(o);
    }

    public int size() {
        return this.list.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {
        T17_Interview1_LockSupport t = new T17_Interview1_LockSupport();
        t2 = new Thread(() -> {
//            while (t.size() != 5) {
                LockSupport.park();
//            }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);

        });
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println(i);
                if (t.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
