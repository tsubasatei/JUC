package com.xt.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 比较 Atomic、Sync、LongAdd
 */
public class T02_Atomic_Sync_LongAdder {
    static long count1 = 0;
    static AtomicLong count2 = new AtomicLong(0);
    static LongAdder count3 = new LongAdder();

    synchronized void m1() {
        for (int i = 0; i < 100; i++) {
            count1++;
        }
    }
    void m2() {
        for (int i = 0; i < 100; i++) {
            count2.incrementAndGet();
        }
    }
    void m3() {
        for (int i = 0; i < 100; i++) {
            count3.increment();
        }
    }

    public static void main(String[] args) {
        T02_Atomic_Sync_LongAdder t = new T02_Atomic_Sync_LongAdder();
        testSync(t);
        testAtomic(t);
        testLongAdder(t);
    }

    private static void testLongAdder(T02_Atomic_Sync_LongAdder t) {
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(t::m3));
        }
        threads.forEach(o -> o.start());
        threads.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("LongAdder：" + count3 + "\ttime：" + (end - start));
    }

    private static void testAtomic(T02_Atomic_Sync_LongAdder t) {
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(t::m2));
        }
        threads.forEach(o -> o.start());
        threads.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("Atomic：" + count2 + "\ttime：" + (end - start));
    }

    private static void testSync(T02_Atomic_Sync_LongAdder t) {
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(t::m1));
        }
        threads.forEach(o -> o.start());
        threads.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("sync：" + count1 + "\ttime：" + (end - start));

    }
}
