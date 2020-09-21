package com.xt.juc.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLcok: 可重入锁
 */
public class T03_ReentrantLock {
    int count = 0;
    Lock lock = new ReentrantLock();
    void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() + "\t" + count);
                if (count == 2) m2();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void m2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " m2()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock t = new T03_ReentrantLock();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
