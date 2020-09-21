package com.xt.juc.container;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T15_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.transfer("aaa");
//        queue.put("bbb");
//        System.out.println(queue);
//        new Thread(() -> {
//            try {
//                queue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
