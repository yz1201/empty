package concurrent.clazz4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tyz1201
 * @datetime 2020-04-09 12:25
 * 线程池中只有一个线程，保证任务的顺序执行
 **/
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> System.out.println(Thread.currentThread().getName() + " -> " + j));
        }
    }
}
