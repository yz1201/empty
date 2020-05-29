package cn.dbdj1201.iconcurrent.biasedlock;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-28 11:55
 **/
@Slf4j(topic = "c.Demo2")
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
//        final Object lock = new Object();
//        log.debug("lock start : {}", ClassLayout.parseInstance(lock).toPrintable());//偏向锁
//        new Thread(() -> {
//            log.debug("lock t1 : {}", ClassLayout.parseInstance(lock).toPrintable());//偏向锁
//            synchronized (lock) {
//                try {
//                    log.debug("lock wait before : {}", ClassLayout.parseInstance(lock).toPrintable());//偏向锁
//                    lock.wait();
//                    log.debug("lock wait after : {}", ClassLayout.parseInstance(lock).toPrintable());//10
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "t-1").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//            synchronized (lock) {
//                log.debug("lock notify before : {}", ClassLayout.parseInstance(lock).toPrintable());
//                lock.notify();
//                log.debug("lock notify after : {}", ClassLayout.parseInstance(lock).toPrintable());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(() -> {
//            synchronized (lock) {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.debug("lock notify before : {}", ClassLayout.parseInstance(lock).toPrintable());//10
//                lock.notifyAll();
//                log.debug("lock notify after : {}", ClassLayout.parseInstance(lock).toPrintable());//
//            }
//        }, "t-2").start();

        test3();
    }

    private static void test3() throws InterruptedException {
        Vector<Object> list = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Object d = new Object();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("===============> ");
            for (int i = 0; i < 30; i++) {
                Object d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t2");
        t2.start();
    }
}
