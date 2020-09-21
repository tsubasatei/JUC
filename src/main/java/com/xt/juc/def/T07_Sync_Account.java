package com.xt.juc.def;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁：容易产生脏读 dirtyRead
 *
 */
public class T07_Sync_Account {

    String name;
    Double balance;

    public synchronized void set(String name, Double balance) {
        this.name = name;
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        this.balance = balance;
    }

    public /*synchronized*/ Double get(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        T07_Sync_Account account = new T07_Sync_Account();
        new Thread(() -> account.set("z3", 50d)).start();
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(account.get("z3"));
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(account.get("z3"));


    }
}
