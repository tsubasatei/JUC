package com.xt.juc.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class T01_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println("callable");
            return 1024;
        });
        new Thread(task).start();
        System.out.println(task.get());
    }
}
