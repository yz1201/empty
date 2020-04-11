package cn.dbdj1201.concurrent.clazz4;

import java.util.concurrent.*;

/**
 * @author tyz1201
 * @datetime 2020-04-09 11:36
 **/
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> future = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());
    }
}
