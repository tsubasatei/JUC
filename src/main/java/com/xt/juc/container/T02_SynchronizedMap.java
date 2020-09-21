package com.xt.juc.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// 284  60944
public class T02_SynchronizedMap {
    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<>());
    static Map<UUID, UUID> m3 = new ConcurrentHashMap<>();

    public static int count = Constants.COUNT;
    public static int threadCount = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }
    static class MyThread extends Thread{
        int start;
        int gap = count / threadCount;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (count / threadCount));
        }
        Arrays.stream(threads).forEach(o -> o.start());
        Arrays.stream(threads).forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("hashtable: " + "\t write time" + (end - start));

        // --------读操作-----
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            for (int j = 0; j < 100000; j++) {
                threads[i] = new Thread(() -> m.get(10));
            }
        }
        Arrays.stream(threads).forEach(o -> o.start());
        Arrays.stream(threads).forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        end = System.currentTimeMillis();
        System.out.println("hashtable: " + "\t read time" + (end - start));
    }
}
