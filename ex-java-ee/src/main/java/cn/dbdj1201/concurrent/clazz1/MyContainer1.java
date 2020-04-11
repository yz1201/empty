package cn.dbdj1201.concurrent.clazz1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-08 12:01
 * 两个方法 add size
 * 两个线程，一个是监听线程，一个是往容器内添加线程，添加为5个时结束
 **/
public class MyContainer1 {

    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        final Object lock = new Object();
        MyContainer1 container1 = new MyContainer1();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 is coming!");
                if (container1.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 over -> " + container1.size());
                lock.notify();
            }
        }).start();

        new Thread(() -> {
            System.out.println("t1 is coming");
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    container1.add(new Object());
                    System.out.println("list add -> " + container1.size());
                    if (container1.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();

    }

    void add(Object obj) {
        list.add(obj);
    }

    int size() {
        return list.size();
    }
}
