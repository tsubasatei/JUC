package com.xt.juc.container;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T11_ArrayBlockingQueue {
    static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            queue.put("a" + i);
        }
//        queue.put("aaa"); // 满了阻塞
//        queue.add("aaa"); // java.lang.IllegalStateException: Queue full
        boolean aaa = queue.offer("aaa");
        System.out.println(aaa); // false
        boolean bbb = queue.offer("bbb", 1, TimeUnit.SECONDS);
        System.out.println(bbb); // false
    }
}
