package com.xt.juc.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T08_ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        System.out.println(System.currentTimeMillis());
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("1------延迟一秒执行，每三秒执行一次");
            System.out.println(System.currentTimeMillis());
        },1,3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("2------延迟一秒执行，每三秒执行一次");
                System.out.println(System.currentTimeMillis());
            }
        },1,3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("3-------延迟一秒执行，每三秒执行一次");
                System.out.println(System.currentTimeMillis());
            }
        },1,3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("4--------延迟一秒执行，每三秒执行一次");
                System.out.println(System.currentTimeMillis());
            }
        },1,3, TimeUnit.SECONDS);
//        scheduledExecutorService.shutdown();
    }
}
