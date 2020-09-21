package com.xt.juc.container;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T22_InterviewA1B2_TransferQueue {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    public static void main(String[] args) throws IOException {
        TransferQueue<Character>  queue = new LinkedTransferQueue<>();

        t1 = new Thread(() -> {
            for (char c : aI) {
                try {
                    Character take = queue.take();
                    System.out.print(take);
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2 = new Thread(() -> {
            for (char c : aC) {
                try {
                    queue.transfer(c);
                    Character take = queue.take();
                    System.out.print(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
