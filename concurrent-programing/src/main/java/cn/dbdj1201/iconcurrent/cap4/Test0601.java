package cn.dbdj1201.iconcurrent.cap4;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tyz1201
 * @datetime 2020-06-01 10:38
 **/
@Slf4j(topic = "c.Test0601")
public class Test0601 {
    private static final ReentrantLock lock = new ReentrantLock();

    @SneakyThrows
    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//            try {
//                log.debug("wanna");
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                log.debug("none return");
//                return;
//            }
//
//            try {
//                log.debug("retrieved");
//            } finally {
//                lock.unlock();
//            }
//        }, "t1");
//        lock.lock();
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();
        lock.lock();
        test();
    }

    private static void test() {
        Thread thread = new Thread(() -> {
            log.debug("retrieve lock");
            try {
                if (!lock.tryLock(5,TimeUnit.SECONDS)) {
                    log.debug("can't");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.debug("come here");
            } finally {
                lock.unlock();
            }
        }, "t2");

        thread.start();
    }

    private static void test2(){
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        lock.lock();
        try {
            condition1.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        condition1.signal();



    }
}
