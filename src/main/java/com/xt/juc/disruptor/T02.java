package com.xt.juc.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

public class T02 {
    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, Executors.defaultThreadFactory());
        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();


        EventTranslator<LongEvent> translator = new EventTranslator<>() {
            @Override
            public void translateTo(LongEvent longEvent, long l) {
                longEvent.setValue(888L);
            }
        };

        ringBuffer.publishEvent(translator);

        EventTranslatorOneArg<LongEvent, Long> translatorOneArg = new EventTranslatorOneArg<>() {
            @Override
            public void translateTo(LongEvent longEvent, long l, Long aLong) {
                longEvent.setValue(aLong);
            }
        };
        ringBuffer.publishEvent(translatorOneArg, 889L);

        EventTranslatorTwoArg<LongEvent, Long, Long> translatorTwoArg = new EventTranslatorTwoArg<LongEvent, Long, Long>() {
            @Override
            public void translateTo(LongEvent longEvent, long l, Long aLong, Long aLong2) {
                longEvent.setValue(aLong + aLong2);
            }
        };
        ringBuffer.publishEvent(translatorTwoArg, 100L, 200L);

        EventTranslatorVararg<LongEvent> translatorVararg = new EventTranslatorVararg<LongEvent>() {
            @Override
            public void translateTo(LongEvent longEvent, long l, Object... objects) {
                long result = 0;
                for (int i = 0; i < objects.length; i++) {
                    result += (Long)objects[i];
                }
                longEvent.setValue(result);
            }
        };
        ringBuffer.publishEvent(translatorVararg, 100L, 200L, 300L);
    }
}
