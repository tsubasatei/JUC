package com.xt.juc.cas;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 弱引用：只要GC就会回收
 */
public class T26_WeakReference {
    public static void main(String[] args) {
        WeakReference m = new WeakReference(new M());
        System.out.println(m.get()); // com.xt.juc.cas.M@13c78c0b
        System.gc();
        System.out.println(m.get()); // null

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
