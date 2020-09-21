package com.xt.juc.cas;

import java.util.concurrent.Exchanger;

/**
 * exchanger:两个线程交换数据
 */
public class T12_Exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
                System.out.println(Thread.currentThread().getName() + "\t" + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
                System.out.println(Thread.currentThread().getName() + "\t" + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
