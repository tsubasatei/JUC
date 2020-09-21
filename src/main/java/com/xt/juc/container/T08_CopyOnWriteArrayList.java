package com.xt.juc.container;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 * 
 * CopyOnWriteArrayList
 *      time:2
 *      size:1000
 *
 * synchronizedList
 *  time:1
 *  size:1000
 */
public class T08_CopyOnWriteArrayList {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
//        List<Integer> list = Collections.synchronizedList(new LinkedList<>());
        Random r = new Random();

        Thread thread = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                list.add(r.nextInt(1000));
            }
        });
        long start = System.currentTimeMillis();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start));
        System.out.println("size:" + list.size());
    }
}
