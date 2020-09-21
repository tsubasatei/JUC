package com.xt.juc.cas;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal: 本地线程
 */
public class T23_ThreadLocal {
    static volatile ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(tl.get()); // null
        }).start();
        new Thread(() -> {
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            tl.set(new Person());
        }).start();
    }
}
class Person{
    String name = "z3";
}
