package com.xt.juc.cas;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题12: 写一个固定容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 */
public class T20_Interview2_Lock {
    LinkedList list = new LinkedList();
    static final int MAX = 10;
    int count = 0;
    Lock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public void put(Object o) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(o);
            count++;
            System.out.println(Thread.currentThread().getName() + " put " + o);
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            Object o = list.removeFirst();
            count--;
            System.out.println(Thread.currentThread().getName() + " get " + o);
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        T20_Interview2_Lock t = new T20_Interview2_Lock();
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
