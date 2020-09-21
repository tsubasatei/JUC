package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * 线程是程序中的不同执行路径
 * T1、main 交替输出（可能连续输出T1 或 main）
 */
public class T01_WhatIsThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 暂停一会儿线程
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        for (int i = 0; i < 10; i++) {
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("main");
        }
    }
}
