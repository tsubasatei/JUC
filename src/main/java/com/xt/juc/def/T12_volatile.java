package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * volatile: 保证线程的可见性、禁止指令重排序
 */
public class T12_volatile {
    volatile boolean flag = true;
    public void m() {
        System.out.println("m start");
        while (flag) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T12_volatile t = new T12_volatile();
        new Thread(t::m).start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        t.flag = false;
    }
}
