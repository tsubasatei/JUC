package com.xt.juc.def;

/**
 * 线程状态：New、Runnable（Ready、Running）、Waiting、TimedWaiting、Blocked、Terminated
 */
public class T04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState()); // RUNNABLE
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread(); // NEW
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState()); // RUNNABLE
        t.join();
        System.out.println(t.getState()); // TERMINATED
    }
}
