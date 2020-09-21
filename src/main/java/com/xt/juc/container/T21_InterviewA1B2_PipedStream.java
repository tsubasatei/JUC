package com.xt.juc.container;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 两个线程交替输出
 * A1B2C3
 */
public class T21_InterviewA1B2_PipedStream {
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aI = "1234567".toCharArray();
    static Thread t1, t2;
    public static void main(String[] args) throws IOException {
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();
        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your turn";

        t1 = new Thread(() -> {
            byte[] buffer = new byte[9];
            for (char c : aC) {
                System.out.print(c);
                try {
                    output1.write(msg.getBytes());
                    input1.read(buffer);
                    if (!new String(buffer).equals(msg)) {
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t2 = new Thread(() -> {
            byte[] buffer = new byte[9];
            for (char c : aI) {
                try {
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print(c);
                        output2.write(msg.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
