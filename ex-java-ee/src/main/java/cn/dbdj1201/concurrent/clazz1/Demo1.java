package cn.dbdj1201.concurrent.clazz1;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author tyz1201
 * @datetime 2020-03-31 18:37
 **/
public class Demo1 {

    private static final AtomicStampedReference<Integer> count = new AtomicStampedReference<Integer>(1, 1);

    public static void main(String[] args) {

        Thread mainThread = new Thread(() -> {
            int val = count.getReference();
            int stamp = count.getStamp();
            System.out.println("val->" + val + "->stamp ->" + stamp);
            try {
                Thread.sleep(2000);
                System.out.println("i am awake->" + Thread.currentThread().getName() + "val->" + val + "->stamp ->" + stamp);
                System.out.println("i am awake->" + Thread.currentThread().getName() + "val->" + count.getReference() + "->stamp ->" + count.getStamp());
                System.out.println("i am awake->" + Thread.currentThread().getName() + "->" + (count.compareAndSet(val, ++val, stamp, ++stamp)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "main thread");

        Thread otherThread = new Thread(() -> {
            try {
                Thread.sleep(20);
                int val = count.getReference();
                int stamp = count.getStamp();
                System.out.println(count.compareAndSet(val, ++val, stamp, ++stamp));
                System.out.println(count.compareAndSet(val, ++val, stamp, ++stamp));
                System.out.println(Thread.currentThread().getName() + "->" + stamp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "other thread");

        mainThread.start();
        otherThread.start();
    }
}
