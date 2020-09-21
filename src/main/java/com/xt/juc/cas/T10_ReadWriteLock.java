package com.xt.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class T10_ReadWriteLock {
    static Lock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        lock.lock();
        try {
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("read over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int value) {
        lock.lock();
        try {
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("write over: " + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        Runnable r = () -> read(lock);
        Runnable r = () -> read(readLock);
//        Runnable w = () -> write(lock, 18);
        Runnable w = () -> write(writeLock, 18);
        for (int i = 0; i < 18; i++) {
            new Thread(r).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(w).start();
        }
    }
}
