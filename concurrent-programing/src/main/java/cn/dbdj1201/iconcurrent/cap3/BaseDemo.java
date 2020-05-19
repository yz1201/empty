package cn.dbdj1201.iconcurrent.cap3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-19 11:16
 **/
@Slf4j(topic = "c.BaseDemo")
public class BaseDemo {
    /*
    线程的创建方式:
        thread 匿名内部类
        runnable匿名内部类，传参给thread
        实现runnable，传参
        callable->FutureTask->Thread
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(() -> log.debug("running"), "t-1");
        thread.start();

        Runnable runnable = () -> log.debug("he he");
        new Thread(runnable, "he he").start();
        new Thread(new TestRunnable(), "wa").start();
        FutureTask<Integer> ft = new FutureTask<>(() -> {
            log.debug("future?");
            TimeUnit.SECONDS.sleep(2);
            return 100;
        });
        new Thread(ft, "future").start();
        log.debug("running");

        log.debug("{} lalala",ft.get());
    }
}

@Slf4j(topic = "c.TestRunnable")
class TestRunnable implements Runnable {
    @Override
    public void run() {
        log.debug("wa");
    }
}