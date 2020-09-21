package com.xt.juc.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T06_FixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start, end;
        start = System.currentTimeMillis();
        getPrime(1, 2000000);
        end = System.currentTimeMillis();
        System.out.println(end - start);

        final int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuCoreNum); //8
        ExecutorService executorService = Executors.newFixedThreadPool(cpuCoreNum);
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 2000000);

        Future<List<Integer>> f1 = executorService.submit(t1);
        Future<List<Integer>> f2 = executorService.submit(t2);
        Future<List<Integer>> f3 = executorService.submit(t3);
        Future<List<Integer>> f4 = executorService.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);

        executorService.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> list = getPrime(startPos, endPos);
            return list;
        }
    }

    // 是否是质数
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }
}
