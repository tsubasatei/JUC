package com.xt.juc.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLcok: 可重入锁
 * tryLock(): 进行尝试锁定，不管是否锁定都可以继续执行，自己决定要不要wait
 * lockInterruptibly: 可以对interrupt()响应，加锁被打断
 */
public class T05_ReentrantLock_lockInterruptibly {
    Lock lock = new ReentrantLock();
    void m1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "要开始沉睡");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " 睡完了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void m2() {
        boolean locked = false;
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " start");
            locked = true;
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " 被打断");
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock_lockInterruptibly t = new T05_ReentrantLock_lockInterruptibly();
        new Thread(t::m1).start();
        Thread t2 = new Thread(t::m2);
        t2.start();
        t2.interrupt();
    }
}
