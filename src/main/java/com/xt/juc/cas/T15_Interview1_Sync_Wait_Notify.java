package com.xt.juc.cas;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题1：实现一个容器，提供两个方法 add、size，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实时监控元素个数，当个数到5个时，给出提示并结束
 */
public class T15_Interview1_Sync_Wait_Notify {
    List list = new ArrayList();
    static final Object o = new Object();

    public void add(Object o) {
        this.list.add(o);
    }

    public int size(){
        return this.list.size();
    }

    public static void main(String[] args) {
        T15_Interview1_Sync_Wait_Notify t = new T15_Interview1_Sync_Wait_Notify();
        Thread t2 = new Thread(() -> {
            synchronized (o) {
                while (t.size() != 5) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                o.notify();
            }
        });
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; i++) {
                    if (t.size() == 5) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    t.add(new Object());
                    System.out.println(i);
                    o.notify();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
