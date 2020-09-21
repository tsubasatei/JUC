package com.xt.juc.def;

/**
 * 对共享资源的操作加锁
 * 只加 volatile 只是保证可见性，不能保证原子性
 * synchronized 既可以保证可见性，又可以保证原子性
 */
public class T05_Sync_Count {
    private /*volatile*/ int count = 10;
    public synchronized void minus() {
        count--;
        System.out.println(Thread.currentThread().getName() + "\t " + count);
    }

    public static void main(String[] args) {
        T05_Sync_Count t = new T05_Sync_Count();
        for (int i = 0; i < 10; i++) {
            new Thread(t::minus).start();
        }
    }
}
