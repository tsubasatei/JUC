package com.xt.juc.pool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T09_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors()); //8

        for (int i = 0; i < 8; i++) {
            executorService.execute(new R(1000 * i));
        }
        executorService.execute(new R(1000));
        System.in.read();
    }

    static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            // 暂停一会儿线程
            try { TimeUnit.MILLISECONDS.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t" + time);
        }
    }
}
