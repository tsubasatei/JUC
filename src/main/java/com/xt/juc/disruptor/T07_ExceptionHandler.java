package com.xt.juc.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

public class T07_ExceptionHandler {
    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, Executors.defaultThreadFactory(),
                ProducerType.MULTI, new SleepingWaitStrategy());

        EventHandler<LongEvent> handler = new EventHandler<>() {
            @Override
            public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
                System.out.println("消费者抛出异常");
            }
        };
        disruptor.handleEventsWith(handler);
        disruptor.handleExceptionsFor(handler).with(new ExceptionHandler<LongEvent>() {
            @Override
            public void handleEventException(Throwable throwable, long l, LongEvent longEvent) {
                throwable.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable throwable) {
                System.out.println("Exception handleOnStartException");
            }

            @Override
            public void handleOnShutdownException(Throwable throwable) {
                System.out.println("Exception handleOnShutdownException");
            }
        });

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        final int threadCount = 1;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            service.submit(()-> {
                System.out.printf("Thread %s ready to start!\n", finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    ringBuffer.publishEvent((event, sequence) -> event.setValue(finalI));
                    System.out.println("生产了" + finalI);
                }
            });
        }
        service.shutdown();
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(LongEventHandler.count);
    }
}
