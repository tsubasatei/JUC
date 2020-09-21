package com.xt.juc.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.IOException;

public class T03 {
    public static void main(String[] args) throws IOException {
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent((event, sequence) -> event.setValue(1000L));
        System.in.read();
    }
}
