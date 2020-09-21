package com.xt.juc.pool;


import lombok.SneakyThrows;

import java.util.concurrent.*;

public class T03_ThreadPoolExecutor {

    static class Task implements Runnable {
        int i;

        public Task(int i) {
            this.i = i;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " task-" + i);
            System.in.read();
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }
    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 8; i++) {
            pool.execute(new Task(i));
        }

        System.out.println(pool.getQueue());
        pool.execute(new Task(100));
        System.out.println(pool.getQueue());
        pool.shutdown();
    }
}
