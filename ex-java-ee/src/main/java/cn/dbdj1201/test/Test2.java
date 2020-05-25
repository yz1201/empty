package cn.dbdj1201.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-25 12:34
 **/
public class Test2 {
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        /*在 main 方法中创建两个线程（使用 Lambda 表达式创建），每个线程都往集合中添加一万个元素，主线程打印元素数量，要解决原子性问题。*/
        List<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(list.size());
    }
}
