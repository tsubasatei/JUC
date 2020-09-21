package com.xt.juc.container;

import java.util.concurrent.ConcurrentLinkedQueue;

public class T09_ConcurrentQueue {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer("queue" + i);
        }
        System.out.println(queue); // [queue0, queue1, queue2, queue3, queue4, queue5, queue6, queue7, queue8, queue9]
        System.out.println(queue.size()); // 10

        System.out.println(queue.poll()); // queue0
        System.out.println(queue.size()); // 9

        System.out.println(queue.peek()); // queue1
        System.out.println(queue.size()); // 9
    }
}
