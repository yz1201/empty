package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tyz1201
 * @datetime 2020-05-26 9:31
 **/
@Slf4j(topic = "c.CountDemo")
public class CountDemo {
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                count.addAndGet(1);
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                count.addAndGet(1);
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println(count.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
