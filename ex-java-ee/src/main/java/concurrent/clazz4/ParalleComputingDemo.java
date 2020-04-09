package concurrent.clazz4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author tyz1201
 * @datetime 2020-04-09 11:49
 **/
public class ParalleComputingDemo {
    private static ExecutorService service = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long timeStart = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 2000000);
        long timeEnd = System.currentTimeMillis();
        System.out.println("it costs main -> " + (timeEnd - timeStart));

        MyTask task1 = new MyTask(1, 300000);
        MyTask task2 = new MyTask(300001, 600000);
        MyTask task3 = new MyTask(600001, 900000);
        MyTask task4 = new MyTask(900001, 1200000);
        MyTask task5 = new MyTask(1200001, 1500000);
        MyTask task6 = new MyTask(1500001, 1800000);
        MyTask task7 = new MyTask(1800001, 1900000);
        MyTask task8 = new MyTask(1900001, 200000);

        long start = System.currentTimeMillis();

        submitTask(task1).get();
        submitTask(task2).get();
        submitTask(task3).get();
        submitTask(task4).get();
        submitTask(task5).get();
        submitTask(task6).get();
        submitTask(task7).get();
        submitTask(task8).get();

        long end = System.currentTimeMillis();

        System.out.println("it costs -> " + (end - start));
    }

    static Future<List<Integer>> submitTask(MyTask task) {
        return service.submit(task);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> list = getPrime(startPos, endPos);
            return list;
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i))
                list.add(i);
        }
        return list;
    }
}
