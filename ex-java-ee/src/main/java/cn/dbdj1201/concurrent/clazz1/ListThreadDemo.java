package cn.dbdj1201.concurrent.clazz1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tyz1201
 * @datetime 2020-04-08 11:39
 * 两个方法 add size
 * 两个线程，一个是监听线程，一个是往容器内添加线程，添加为5个时结束
 **/
public class ListThreadDemo {
    private AtomicInteger count = new AtomicInteger(0);
    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        ListThreadDemo demo = new ListThreadDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    demo.add();
                    System.out.println("list add -> " + threads.size());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (demo.size() == 5)
                    break;
            }
            System.out.println(" over -> " + threads.size());
        }).start();

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(demo.size());
    }

    void add() {
        threads.add(new Thread(() -> System.out.println("wu liao bu")));
        count.incrementAndGet();
    }

    int size() {
        return count.get();
    }
}
