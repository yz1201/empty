package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tyz1201
 * @datetime 2020-06-01 14:12
 **/
@Slf4j(topic = "c.Test060102")
public class Test060102 {
    private static final Object lock = new Object();
    private static ReentrantLock lock2 = new ReentrantLock();
    private static Condition condition = lock2.newCondition();
    private static boolean t2Round = false;

    public static void main(String[] args) {
//        Thread t1 = new Thread(() -> {
//            synchronized (lock) {
//                while (!t2Round) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            log.debug("1");
//        }, "t1");
//
//        Thread t2 = new Thread(() -> {
//            synchronized (lock) {
//                log.debug("2");
//                t2Round = true;
//                lock.notifyAll();
//            }
//        }, "t2");
//        t1.start();
//        t2.start();
        test3();
    }

    private static void test() {
        new Thread(() -> {
            lock2.lock();
            try {
                while (!t2Round) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
            }
            log.debug("1");
        }, "t1").start();

        new Thread(() -> {
            lock2.lock();
            try {
                log.debug("2");
                t2Round = true;
                condition.signalAll();
            } finally {
                lock2.unlock();
            }
        }, "t2").start();
    }

    private static void test2() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        t1.start();

        new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        }, "t1").start();
    }

    private static int num = 1;

    private static void test3() {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (num % 3 != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("a");
                    num++;
                    lock.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (num % 3 != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("b");
                    num++;
                    lock.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (num % 3 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("c");
                    num++;
                    lock.notifyAll();
                }
            }
        }).start();
    }
}
