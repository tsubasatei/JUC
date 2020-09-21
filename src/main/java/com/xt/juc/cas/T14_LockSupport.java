package com.xt.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport:park、unpark实现阻塞、唤醒线程
 */
public class T14_LockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LockSupport.unpark(Thread.currentThread());
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 8) {
                    LockSupport.park();
                }
            }
        });
        t.start();
//        LockSupport.unpark(t);
    }
}
