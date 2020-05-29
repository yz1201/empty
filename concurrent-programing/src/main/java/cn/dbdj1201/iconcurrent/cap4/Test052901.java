package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author tyz1201
 * @datetime 2020-05-29 10:23
 **/
@Slf4j(topic = "c.Test052901")
public class Test052901 {
    static final Object obj = new Object();

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        synchronized (obj) {
// 同步块 A
            log.debug(ClassLayout.parseInstance(obj).toPrintable());
            method2();
            log.debug("2after {}",ClassLayout.parseInstance(obj).toPrintable());
        }
    }

    public static void method2() {
        new Thread(()->{
            synchronized (obj) {
                log.debug(ClassLayout.parseInstance(obj).toPrintable());
// 同步块 B
            }
        }).start();

    }
}
