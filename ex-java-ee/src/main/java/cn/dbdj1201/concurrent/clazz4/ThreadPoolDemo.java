package cn.dbdj1201.concurrent.clazz4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-09 11:22
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            fixedThreadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> ");
            });
        }

        System.out.println(fixedThreadPool);

        fixedThreadPool.shutdown();
        System.out.println(fixedThreadPool.isTerminated());
        System.out.println(fixedThreadPool.isShutdown());

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(fixedThreadPool.isTerminated()); //池子中的任务是否被完成
        System.out.println(fixedThreadPool.isShutdown());   //是否被中止
        System.out.println(fixedThreadPool);
    }
}
