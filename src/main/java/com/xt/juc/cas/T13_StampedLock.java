package com.xt.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock: 优化可重入读写锁
 */
public class T13_StampedLock {
    static StampedLock lock = new StampedLock();
    static long milli = 5000;
    static int count = 0;
    // 悲观写锁
    public static long writLock() {
        long stamp = lock.writeLock();
        count++;
        lock.unlockWrite(stamp);
        return System.currentTimeMillis();
    }
    // 悲观读锁
    public static void readLock() {
        int currentCount = 0;
        long stamp = lock.readLock();
        currentCount = count;
        // 暂停一会儿线程
        try { TimeUnit.MILLISECONDS.sleep(milli); } catch (InterruptedException e) { e.printStackTrace(); }
        lock.unlockRead(stamp);
        System.out.println("readLock: " + currentCount);
    }
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        readLock();
        long l2 = writLock();
        System.out.println(l2 - l1);
    }
}
