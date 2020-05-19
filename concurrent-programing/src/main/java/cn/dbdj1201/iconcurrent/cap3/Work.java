package cn.dbdj1201.iconcurrent.cap3;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-19 20:02
 **/
@Slf4j(topic = "c.Work")
public class Work {
    /*
    用两个线程（两个人协作）模拟烧水泡茶过程
        想泡壶茶喝。当时的情况是：开水没有；水壶要洗，茶壶、茶杯要洗；火已生了，茶叶也有了。怎么办？
    */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                log.debug("烧水冲冲冲");
                washKettle();
                boilingWater();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "吃水忘了烧水人");

        Thread t2 = new Thread(() -> {
            try {
                log.debug("我准备泡茶");
                washTeapot();
                washCup();
                pickTea();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "我只会洗茶杯");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.debug("完事，可以泡茶了");
    }


    /*洗水壶1分钟*/
    private static void washKettle() throws InterruptedException {
        System.out.println("洗水壶呢，等着吧");
        TimeUnit.SECONDS.sleep(1);
    }

    private static void washTeapot() throws InterruptedException {
        System.out.println("洗茶壶呢，急啥");
        TimeUnit.SECONDS.sleep(1);
    }

    private static void boilingWater() throws InterruptedException {
        System.out.println("烧水只需15分钟");
        TimeUnit.SECONDS.sleep(15);
    }

    private static void pickTea() throws InterruptedException {
        System.out.println("去拿个茶叶");
        TimeUnit.SECONDS.sleep(1);
    }

    private static void washCup() throws InterruptedException {
        System.out.println("洗茶杯");
        TimeUnit.SECONDS.sleep(2);
    }
}
