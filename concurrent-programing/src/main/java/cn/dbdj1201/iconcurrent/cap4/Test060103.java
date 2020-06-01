package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tyz1201
 * @datetime 2020-06-01 15:01
 **/
@Slf4j(topic = "c.Test060103")
public class Test060103 {

    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {
//        WaitNotify waitNotify = new WaitNotify(1, 5);
//        new Thread(() -> waitNotify.print("a", 1, 2)).start();
//        new Thread(() -> waitNotify.print("b", 2, 3)).start();
//        new Thread(() -> waitNotify.print("c", 3, 1)).start();
//        LockApp lockApp = new LockApp(5);
//        Condition a = lockApp.newCondition();
//        Condition b = lockApp.newCondition();
//        Condition c = lockApp.newCondition();
//        new Thread(() -> lockApp.print("a", a, b)).start();
//        new Thread(() -> lockApp.print("b", b, c)).start();
//        new Thread(() -> lockApp.print("c", c, a)).start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lockApp.lock();
//        try {
//            a.signal();
//        } finally {
//            lockApp.unlock();
//        }


        ParkUnpark parkUnpark = new ParkUnpark(5);
        t1 = new Thread(() -> parkUnpark.print("a", t2), "t1");
        t2 = new Thread(() -> parkUnpark.print("b", t3), "t1");
        t3 = new Thread(() -> parkUnpark.print("c", t1), "t1");

        t1.start();
        t2.start();
        t3.start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t1);
    }
}

class WaitNotify {
    private int flag;
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {

            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}

class LockApp extends ReentrantLock {

    private final int loopNumber;

    public LockApp(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }

        }
    }
}

class ParkUnpark {
    private final int loopNumber;

    public ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}