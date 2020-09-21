package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * 程序执行过程中，如果出现异常，默认情况下会释放锁
 */
public class T11_Sync_Exception {
    int count = 0;
    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + "\t " + count);
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            if (count == 5) {
                int i = 10 / 0; // 抛异常，锁被释放，程序乱入，不想被释放，try。。。catch
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T11_Sync_Exception t = new T11_Sync_Exception();
        new Thread(t::m, "t1").start();
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(t::m, "t2").start();
    }
}
