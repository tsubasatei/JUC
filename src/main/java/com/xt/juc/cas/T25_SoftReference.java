package com.xt.juc.cas;

import java.lang.ref.SoftReference;

/**
 * 软引用，有空间不回收，空间不够就回收。应用：做缓存
 * -Xms20M -Xmx20M
 */
public class T25_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get()); // [B@2cb4c3ab
        System.gc();
        System.out.println(softReference.get()); // [B@2cb4c3ab
        byte[] bytes = new byte[1024 * 1024 * 15];
        System.gc();
        System.out.println(softReference.get()); // null

    }
}
