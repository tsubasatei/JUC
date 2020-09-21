package com.xt.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLcok: 可重入锁
 * tryLock(): 进行尝试锁定，不管是否锁定都可以继续执行，自己决定要不要wait
 */
public class T04_ReentrantLock_tryLock {
    Lock lock = new ReentrantLock();
    void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 3; i++) {
                // 暂停一会儿线程
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void m2() {
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "\t" + locked);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T04_ReentrantLock_tryLock t = new T04_ReentrantLock_tryLock();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
