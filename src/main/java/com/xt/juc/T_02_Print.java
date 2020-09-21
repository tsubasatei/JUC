package com.xt.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定义一个线程，输出1-10之间的整数，定义另一个线程，逆序输出1-10之间的整数，要求线程A和线程B交替输出
 */
public class T_02_Print {
    static class MyData{
        Lock lock = new ReentrantLock();
        Condition  conditionA = lock.newCondition();
        Condition  conditionB = lock.newCondition();
        int flag = 1;
        int a = 1;
        int b = 10;
        public void printAsc() {
            lock.lock();
            try {
                while (a <= 10) {
                    if (flag != 1) {
                        conditionA.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "-->" + a);
                    a++;
                    flag = 2;
                    conditionB.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
        public void printDesc() {
            lock.lock();
            try {
                while (b > 0) {
                    if (flag != 2) {
                        conditionB.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "-->" + b);
                    b--;
                    flag = 1;
                    conditionA.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            myData.printAsc();
        }, "AA").start();
        new Thread(()->{
            myData.printDesc();
        }, "BB").start();
    }
}
