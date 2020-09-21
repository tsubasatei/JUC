package com.xt.juc.cas;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 跟踪Lock源码
 */
public class T21_TestLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
