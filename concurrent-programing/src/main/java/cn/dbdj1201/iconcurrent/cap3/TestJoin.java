package cn.dbdj1201.iconcurrent.cap3;

import cn.dbdj1201.iconcurrent.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tyz1201
 * @datetime 2020-05-19 17:36
 **/
@Slf4j(topic = "c.TestJoin")
public class TestJoin {
    private static int r1 = 0;
    private static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Sleeper.sleep(1);
            r1 = 10;
        }, "t1");

        Thread t2 = new Thread(() -> {
            Sleeper.sleep(2);
            r2 = 20;
        }, "t2");

        t1.start();
        t2.start();

        //总共只需等待2s
        log.debug("join begin");
        t2.join();
        log.debug("t2 join end");
        t1.join(1000);
        log.debug("t1 join end");
        log.debug("r1: {} r2:{}", r1, r2);
    }
}
