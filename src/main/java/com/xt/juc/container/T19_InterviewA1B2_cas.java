package com.xt.juc.container;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T19_InterviewA1B2_cas {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    enum ReadyToRun {T1, T2}

    static ReadyToRun r = ReadyToRun.T1;
    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (char c : aC) {
                while (ReadyToRun.T1 != r) {

                }
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        });

        t2 = new Thread(() -> {
            for (char c : aI) {
                while (ReadyToRun.T2 != r) {

                }
                System.out.print(c);
                r = ReadyToRun.T1;
            }

        });

        t1.start();
        t2.start();
    }
}
