package cn.dbdj1201.iconcurrent.cap5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tyz1201
 * @datetime 2020-06-19 20:13
 **/
@Slf4j(topic = "c.Test061901")
public class Test061901 {
    static boolean flag = true;
    static int num = 1000;

    public static void main(String[] args) throws InterruptedException {

//        Thread t1 = new Thread(() -> {
//            while (flag) {
//                log.debug(" i am running ");
//            }
//        }, "t1");
//        t1.start();
//        TimeUnit.SECONDS.sleep(1);
//        flag = false;
//        log.debug("stop");
        TestThread testThread = new TestThread();
        for (int i = 0; i < 100; i++) {
            new Thread(testThread, "thread - " + i).start();
        }

        TimeUnit.SECONDS.sleep(3);
        log.debug("num {}", testThread.getNum().get());
    }
}

@Slf4j(topic = "c.TestThread")
class TestThread implements Runnable {

    private final AtomicInteger num = new AtomicInteger(0);

    public AtomicInteger getNum() {
        return num;
    }

    @Override
    public void run() {
//        while (num < 1000) {
//            num++;
//            log.debug("{}", num);
//        }
        for (int i = 0; i < 1000; i++) {
            num.addAndGet(1);
            log.debug("{}", num.get());
        }
    }
}