package com.xt.juc;

import java.util.concurrent.*;

/**
 * 创建线程的四种方式
 */
public class T_01_Thread {
    static class MyThread01 extends Thread{
        @Override
        public void run() {
            System.out.println("创建线程的第一种方式: 继承Thread类");
        }
    }
    static class MyThread02 implements Runnable{
        @Override
        public void run() {
            System.out.println("创建线程的第二种方式：实现Runnable接口");
        }
    }
    static class MyThread03 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("创建线程的第三种方式：实现Callable接口");
            return 1024;
        }
    }

    public static void main(String[] args) {
        new MyThread01().start();

        new Thread(new MyThread02()).start();

        MyThread03 myThread03 = new MyThread03();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread03);
        try {
            new Thread(futureTask).start();
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 创建线程的第四种方式：使用线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 3; i++) {
                threadPool.submit(new FutureTask<String>(() -> {
                    System.out.println("创建线程池的第四种方式: 使用线程池");
                    return Thread.currentThread().getName();
                }));
            }
        } finally {
            threadPool.shutdown();
        }
    }

}
