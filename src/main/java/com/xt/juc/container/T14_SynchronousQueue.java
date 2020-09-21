package com.xt.juc.container;

import java.util.concurrent.SynchronousQueue;

public class T14_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.put("aaa"); // 若前面没有人等着take，则put阻塞
//        queue.add("bbb"); // java.lang.IllegalStateException: Queue full
        System.out.println(queue.size());
    }
}
