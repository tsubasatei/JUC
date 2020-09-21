package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * 父子类可重入 同一把锁
 */
public class T09_Sync_Super {
    public static void main(String[] args) {
        TT tt = new TT();
        new Thread(tt::m).start();
    }
}

class T {
    public synchronized void m() {
        System.out.println("p start");
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("p end.");
    }
}
class TT extends T {
    @Override
    public synchronized void m() {
        System.out.println("c start...");
        super.m();
        System.out.println("p end...");
    }
}
