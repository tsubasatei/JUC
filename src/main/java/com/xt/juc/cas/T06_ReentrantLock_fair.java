package com.xt.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock: 公平锁 和 非公平锁（默认）
 */
public class T06_ReentrantLock_fair {
    Lock lock = new ReentrantLock(true);

    void m() {
        for (int i = 0; i < 3; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T06_ReentrantLock_fair t = new T06_ReentrantLock_fair();
        Thread t1 = new Thread(t::m, "t1");
        Thread t2 = new Thread(t::m, "t2");
        t1.start();
        t2.start();
    }
}
