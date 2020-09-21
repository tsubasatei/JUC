package com.xt.juc.container;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T12_DelayQueue {
    static DelayQueue<MyTask> queue = new DelayQueue<>();
    static class MyTask implements Delayed {
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) return 1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        queue.put(new MyTask("t1", now + 1000));
        queue.put(new MyTask("t2", now + 2000));
        queue.put(new MyTask("t3", now + 500));
        queue.put(new MyTask("t4", now + 2500));
        queue.put(new MyTask("t5", now + 300));

        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take());
        }
    }
}
