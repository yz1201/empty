package cn.dbdj1201.iconcurrent.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author tyz1201
 * @datetime 2020-06-22 10:21
 **/
@Slf4j(topic = "c.Test062201")
public class Test062201 {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) throws InterruptedException {
        String prev = ref.getReference();
        int stamp = ref.getStamp();
        log.debug("main start...ref {} - {}", prev, stamp);

        other();
        TimeUnit.SECONDS.sleep(1);
        log.debug("change A=>C {} now stamp {}", ref.compareAndSet(prev, "C", stamp, 1),ref.getStamp());


    }

    private static void other() {
        new Thread(() -> {
            ref.compareAndSet("A", "B", 0, 1);
            log.debug("ref {} - {}", ref.getReference(), ref.getStamp());
            ref.compareAndSet("B", "A", 1, 2);
            log.debug("ref {} - {}", ref.getReference(), ref.getStamp());
        }, "t-1").start();
    }
}
