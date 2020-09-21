package com.xt.juc.cas;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class T22_VarHandle {
    int x = 8;
    static VarHandle handle;
    static {
        try {
            handle = MethodHandles.lookup().findVarHandle(T22_VarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T22_VarHandle t = new T22_VarHandle();
        System.out.println(handle.get(t)); // 8
        handle.set(t, 9);
        System.out.println(handle.get(t)); // 9
        handle.compareAndSet(t, 9, 10);
        System.out.println(handle.get(t)); // 10
        handle.getAndAdd(t, 10);
        System.out.println(handle.get(t)); // 20
    }

}
