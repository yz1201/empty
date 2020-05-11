package cn.dbdj1201.concurrent.clazz1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tyz1201
 * @datetime 2020-04-08 11:02
 **/
public class AtomicIntegerDemo {
    private static AtomicInteger count = new AtomicInteger(0);
    private static int value1 = 0;
    private static int value2 = 0;

    void m() {
        for (int i = 0; i < 10000000L; i++) {
            count.incrementAndGet();
        }
    }

    void m1() {
        for (int i = 0; i < 10000000L; i++) {
            synchronized (this) {
                value1++;
            }
        }
    }

    synchronized void m2() {
        for (int i = 0; i < 10000000L; i++) {
            value2++;
        }
    }

    void m3() {
        synchronized (this) {
            for (int i = 0; i < 10000000L; i++) {
                value2++;
            }
        }
    }


    public static void main(String[] args) {
        AtomicIntegerDemo demo = new AtomicIntegerDemo();
        Thread t1 = new Thread(demo::m);
        Thread t2 = new Thread(demo::m);
        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println(value2);
        System.out.println("consume time -> " + (System.currentTimeMillis() - startTime));

        Thread t3 = new Thread(demo::m1);
        Thread t4 = new Thread(demo::m1);
        long startTime1 = System.currentTimeMillis();
        t3.start();
        t4.start();
        while (t3.isAlive() || t4.isAlive()) {

        }
        System.out.println(value1);
        System.out.println("consume time1 -> " + (System.currentTimeMillis() - startTime1));
    }
}
