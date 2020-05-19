package cn.dbdj1201.iconcurrent.cap3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-19 18:11
 **/
@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    /*
        打断sleep，wait/join的线程
        处于阻塞状态的线程,被打断后打断标记置为假，
        运行状态的线程被打断，打断标记为真。
     */
    public static void main(String[] args) {

        int i = 1;
        while(i <= 10){
            i++;
            System.out.println("HelloWorld");
        }
        System.out.println(i);
//        Thread t1 = new Thread(() -> {
//            log.debug("sleep ..");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "t1");
//        t1.start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.debug("interrupted");
//        t1.interrupt();
//        log.debug("打断标记： {}",t1.isInterrupted());

        Thread thread = new Thread(() -> {
            do {
                System.out.println("1111");
            } while (!Thread.currentThread().isInterrupted());
        }, "t1");

        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("interrupt");
        thread.interrupt();
    }
}
