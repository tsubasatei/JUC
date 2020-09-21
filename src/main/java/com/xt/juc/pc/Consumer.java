package com.xt.juc.pc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Consumer implements Runnable{
    private BlockingQueue<Goods> blockingQueue;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Goods goods = blockingQueue.take();
                // 暂停一会儿线程
                System.out.println("消费者消费产品：" + goods.getBrand() + "-->" + goods.getName() + "--> " +i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
