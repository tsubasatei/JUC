package com.xt.juc.container;

import java.util.PriorityQueue;

public class T13_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("a");
        queue.add("d");
        queue.add("c");
        queue.add("b");
        for (int i = 0; i < 4; i++) {
            System.out.println(queue.poll());
        }
    }
}
