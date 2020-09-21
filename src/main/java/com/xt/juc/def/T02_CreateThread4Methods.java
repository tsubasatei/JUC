package com.xt.juc.def;

import java.util.concurrent.*;

/**
 * 创建线程的4中方式
 */
public class T02_CreateThread4Methods {
    static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println("创建线程方式一：extends Thread");
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            System.out.println("创建线程方式二：implements Runnable");
        }
    }

    static class T3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("创建线程方式三：implements Callable");
            return "callable";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new T1().run();
        new Thread(new T2()).run();
        FutureTask<String> futureTask = new FutureTask<>(new T3());
        new Thread(futureTask).run();
        System.out.println(futureTask.get());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("创建线程方式四：线程池"));
        executor.shutdown();
    }
}
