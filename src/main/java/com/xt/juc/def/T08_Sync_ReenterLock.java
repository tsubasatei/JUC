package com.xt.juc.def;

/**
 * Synchronized：可重入锁
 */
public class T08_Sync_ReenterLock {
    public synchronized void m1() {
        System.out.println("m1 start");
        m2();
        System.out.println("m1 end.");
    }

    private synchronized void m2() {
        System.out.println("m2 start...");
        System.out.println("m2 end...");
    }

    public static void main(String[] args) {
        T08_Sync_ReenterLock t = new T08_Sync_ReenterLock();
        new Thread(t::m1).start();
    }
}
