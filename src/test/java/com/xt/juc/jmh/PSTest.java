package com.xt.juc.jmh;

import com.xt.juc.pool.T11_ParallelStreamAPI;
import org.openjdk.jmh.annotations.*;

public class PSTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 3)
    public void test () {
        T11_ParallelStreamAPI.forEach();
    }
}
