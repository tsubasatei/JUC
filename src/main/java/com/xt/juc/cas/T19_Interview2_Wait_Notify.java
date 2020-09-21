package com.xt.juc.cas;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 面试题12: 写一个固定容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 */
public class T19_Interview2_Wait_Notify {
    LinkedList list = new LinkedList();
    static final int MAX = 10;
    int count = 0;

    public synchronized void put(Object o) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(o);
        count++;
        System.out.println(Thread.currentThread().getName() + " put " + o);
        this.notifyAll();
    }

    public synchronized void get() {
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object o = list.removeFirst();
        count--;
        System.out.println(Thread.currentThread().getName() + " get " + o);
        this.notifyAll();

    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        T19_Interview2_Wait_Notify t = new T19_Interview2_Wait_Notify();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    t.put(j);
                }
            }, "producer-" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1; j++) {
                    t.get();
                }
            }, "consumer-" + i).start();
        }
    }
}
