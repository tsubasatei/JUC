package com.xt.juc.pool;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T02_CompletableFuture {
    public static void main(String[] args) throws IOException {
        long start, end;
        start = System.currentTimeMillis();
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
        end = System.currentTimeMillis();
        System.out.println(end - start); // 3070

        start = System.currentTimeMillis();
        CompletableFuture.supplyAsync(()->priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price: " + str)
                .thenAccept(System.out::println);
        end = System.currentTimeMillis();
        System.out.println(end - start); // 6
        System.in.read();

    }

    private static double priceOfTM() {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("After 1s sleep");
        return 1d;
    }
    private static double priceOfTB() {
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("After 2s sleep");
        return 2d;
    }
    private static double priceOfJD() {
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("After 3s sleep");
        return 3d;
    }


}
