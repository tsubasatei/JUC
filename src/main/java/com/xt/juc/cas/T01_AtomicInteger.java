package com.xt.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicXXX：CAS + Unsafe
 */
public class T01_AtomicInteger {
    AtomicInteger count = new AtomicInteger(0);
    void m() {
        for (int i = 0; i < 10; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m));
        }
        threads.forEach(o -> o.start());
        threads.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count.get()); // 100
    }
}
