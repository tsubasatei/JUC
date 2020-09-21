package com.xt.juc.container;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T16_InterviewA1B2_LockSupport {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    public static void main(String[] args) {

        t1 = new Thread(()-> {
            for (char c : aC) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(()-> {
            for (char c : aI) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
