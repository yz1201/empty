package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author tyz1201
 * @datetime 2020-05-27 22:17
 **/
@Slf4j(topic = "c.LSD")
public class LockSupportDemo {
    //park unpark

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            log.debug("start...");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
            LockSupport.park();
            log.debug("??????");
            LockSupport.park();
            log.debug("haha");
        }, "t-1");


        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("unpark...");
        LockSupport.unpark(thread);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
