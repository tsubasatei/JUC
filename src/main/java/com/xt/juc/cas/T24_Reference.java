package com.xt.juc.cas;

import java.io.IOException;

/**
 * 强引用，非空，gc不回收
 */
public class T24_Reference {
    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
