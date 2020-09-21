package com.xt.juc.pool;

import java.util.ArrayList;
import java.util.Random;

public class T11_ParallelStreamAPI {
    static ArrayList<Integer> list = new ArrayList<>();
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            list.add(1000000 + r.nextInt(1000000));
        }
    }

    public static void main(String[] args) {
        forEach();
        parallel();
    }

    static void parallel() {
        long start = System.currentTimeMillis();
        list.parallelStream().forEach(o -> isPrime(o));
        long end = System.currentTimeMillis();
        System.out.println("parallel: " + (end - start));
    }

    public static void forEach() {
        long start = System.currentTimeMillis();
        list.forEach(o -> isPrime(o));
        long end = System.currentTimeMillis();
        System.out.println("sequential: " + (end - start));
    }
    static boolean isPrime(int value) {
        for (int i = 2; i <= value / 2; i++) {
            if (value % i == 0) return false;
        }
        return true;
    }
}
