package cn.dbdj1201.concurrent.clazz1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-08 12:17
 * 两个方法 add size
 * 两个线程，一个是监听线程，一个是往容器内添加线程，添加为5个时结束
 **/
public class MyContainer2 {
    private static List<Object> list = new ArrayList<>();

    void add(Object obj) {
        list.add(obj);
    }

    int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer2 container2 = new MyContainer2();
        CountDownLatch latch = new CountDownLatch(5);
        new Thread(() -> {
            System.out.println("t2 gogo");
            if (container2.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 over -> " + container2.size());
        }).start();

        new Thread(() -> {
            System.out.println("t1 goog");
            for (int i = 0; i < 10; i++) {
                list.add(new Object());
                System.out.println("list add -> " + container2.size());
                System.out.println("latch -> " + latch.getCount());
                latch.countDown();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
