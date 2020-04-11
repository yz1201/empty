package cn.dbdj1201.concurrent.clazz2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tyz1201
 * @datetime 2020-04-08 15:50
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完之后m2才能执行
 * 复习synchronized最原始的语义
 * reentrantlock 手动锁定，手动释放锁，碰见异常也不会释放锁
 **/
public class ReentrantLockDemo {

    private ReentrantLock lock = new ReentrantLock();
    private ReentrantLock fiarLock = new ReentrantLock(true);

    void m1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("m1 -> " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized void m2() {
        System.out.println("m2 go go");
    }

    void m3() {

        for (int i = 0; i < 10000; i++) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m3 -> " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    void m4() {
        try {
            lock.lock();
            System.out.println("m4 go go");
        } finally {
            lock.unlock();
        }
    }

    void m5() {
        try {
            boolean b = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m6() {
        for (int i = 0; i < 100; i++) {
            fiarLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            } finally {
                fiarLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        new Thread(demo::m3).start();

        Thread t2 = new Thread(demo::m4);
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(3);
            t2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
