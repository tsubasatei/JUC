package com.xt.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05_CacheThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 2; i++){
            int finalI = i;
            executorService.execute(()->{
                // 暂停一会儿线程
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t" + finalI);
            });
        }
        System.out.println(executorService);
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(80); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(executorService);
        executorService.shutdown();
    }

}
