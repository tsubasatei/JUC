package com.xt.juc;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class T_03_ThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        System.out.println(LocalDateTime.now());
        try {
            /*scheduledExecutorService.schedule(() -> {
                System.out.println("延时3秒执行");
                System.out.println(LocalDateTime.now());
            }, 3L, TimeUnit.SECONDS);*/

            scheduledExecutorService.scheduleAtFixedRate(() -> {
                System.out.println("1--延时1秒执行，每3秒执行一次");
                System.out.println(LocalDateTime.now());
            }, 1, 3, TimeUnit.SECONDS);
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                System.out.println("2--延时1秒执行，每3秒执行一次");
                System.out.println(LocalDateTime.now());
            }, 1, 3, TimeUnit.SECONDS);
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                System.out.println("3--延时1秒执行，每3秒执行一次");
                System.out.println(LocalDateTime.now());
            }, 1, 3, TimeUnit.SECONDS);
        } finally {
//            scheduledExecutorService.shutdown();
        }
    }
}
