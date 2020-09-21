package com.xt.juc.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    public static int count = 0;

    /**
     *
     * @param longEvent 要处理的消息
     * @param seq 位置
     * @param b 整体消息是不是没结束
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent longEvent, long seq, boolean b) throws Exception {
        count++;
        System.out.println(Thread.currentThread().getName() + "\t " + longEvent + " 序号：" + seq);
    }
}
