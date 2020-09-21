package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法和非同步方法可以同时执行
 */
public class T06_Sync_NoSync {
    public synchronized void sync() {
        System.out.println(Thread.currentThread().getName() + " sync start...");
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() + " sync end.");
    }
    public void noSync() {
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() + " no sync start");
    }

    public static void main(String[] args) {
        T06_Sync_NoSync t = new T06_Sync_NoSync();
        new Thread(t::sync).start();
        new Thread(t::noSync).start();
    }
}
