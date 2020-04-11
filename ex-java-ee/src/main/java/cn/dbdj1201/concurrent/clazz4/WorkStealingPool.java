package cn.dbdj1201.concurrent.clazz4;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-09 16:15
 **/
public class WorkStealingPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println(service);

        service.execute(new MyTaskForWork(1000));
        service.execute(new MyTaskForWork(2000));
        service.execute(new MyTaskForWork(2000));
        service.execute(new MyTaskForWork(2000));
        service.execute(new MyTaskForWork(2000));

        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class MyTaskForWork implements Runnable {

        int time;

        public MyTaskForWork(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time + " sleep -> " + Thread.currentThread().getName());
        }
    }
}
