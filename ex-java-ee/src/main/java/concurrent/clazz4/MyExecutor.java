package concurrent.clazz4;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tyz1201
 * @datetime 2020-04-09 11:06
 * Executor
 *      用来执行某一个任务
 * ExecutorService
 *      后台服务，等着执行任务，submit方法，可以执行callable跟runnable
 * Callable 有返回值 其他跟runnable几乎一样
 * Executors 操作执行器的工具类，工厂类。
 **/
public class MyExecutor implements Executor {

    public static void main(String[] args) {
        new MyExecutor().execute(() -> {
            System.out.println("hello executor");
        });

    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
