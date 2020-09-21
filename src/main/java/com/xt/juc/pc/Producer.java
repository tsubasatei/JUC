package com.xt.juc.pc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.BlockingQueue;

@Data
@AllArgsConstructor
public class Producer implements Runnable{
    private BlockingQueue<Goods> blockingDeque;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Goods goods = null;
            if (i % 2 == 0) {
                goods = new Goods("哇哈哈", "矿泉水");
            } else {
                goods = new Goods("旺仔", "小馒头");
            }
            System.out.println("生产者开始生产商品：" + goods.getBrand() + "--" + goods.getName() + "--> " +i);
            try {
                blockingDeque.put(goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
