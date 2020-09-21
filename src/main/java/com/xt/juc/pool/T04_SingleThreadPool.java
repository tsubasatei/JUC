package com.xt.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T04_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5;i++){
            int finalI = i;
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "\t" + finalI);
            });
        }
        executorService.shutdown();
    }
}
