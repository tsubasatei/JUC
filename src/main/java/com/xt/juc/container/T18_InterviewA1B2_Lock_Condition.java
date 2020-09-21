package com.xt.juc.container;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T18_InterviewA1B2_Lock_Condition {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    static Lock lock = new ReentrantLock();
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            lock.lock();
            try {
                for (char c : aC) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t2 = new Thread(() -> {
            lock.lock();
            try {
                for (char c : aI) {
                    System.out.print(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        });

        t1.start();
        t2.start();
    }
}
