package cn.dbdj1201.iconcurrent.biasedlock;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author tyz1201
 * @datetime 2020-05-28 10:51
 **/
@Slf4j(topic = "c.Demo1")
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
//        final Object lock = new Object();
//        int i = lock.hashCode();
//        String s = ClassLayout.parseInstance(lock).toPrintable();
//        log.debug("hashcode: {}", Integer.toBinaryString(i));
//        log.debug(s);
//        synchronized (lock) {
//            log.debug("lock-> {}", ClassLayout.parseInstance(lock).toPrintable());
//        }
//        log.debug("lock-> {}", ClassLayout.parseInstance(lock).toPrintable());

        test2();
    }

    private static void test2() throws InterruptedException {
        Object d = new Object();
        Thread t1 = new Thread(() -> {
            log.debug("t1 start...");
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
            synchronized (Demo1.class) {
                Demo1.class.notify();
            }
            log.debug("t1 over");
            // 如果不用 wait/notify 使用 join 必须打开下面的注释
            // 因为：t1 线程不能结束，否则底层线程可能被 jvm 重用作为 t2 线程，底层线程 id 是一样的
        /*try {
        System.in.read();
        } catch (IOException e) {
        e.printStackTrace();
        }*/
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            log.debug("t2 start...");
            synchronized (Demo1.class) {
                try {
                    Demo1.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
        }, "t2");
        t2.start();
    }
}
