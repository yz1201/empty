package cn.dbdj1201.concurrent.clazz2;

import java.util.LinkedList;

/**
 * @author tyz1201
 * @datetime 2020-04-08 16:30
 * 写一个固定容量的同步容器，拥有get put方法，以及getCount方法
 * 能够支持两个生产者线程以及十个消费者线程的阻塞调用
 **/
public class MyContainer3<T> {
    private LinkedList<T> list = new LinkedList<>();
    private final static int MAX = 10;
    private int count = 0;

    synchronized void put(T t) {
        while (list.size() == MAX) {    //why while
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        ++count;
        this.notifyAll(); //why notifyAll
    }

    synchronized T get() {
        T t;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        --count;
        this.notifyAll();
        return t;
    }

    int getCount() {
        return count;
    }

    public static void main(String[] args) {
        MyContainer3<String> container3 = new MyContainer3<>();
        //消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++)
                    System.out.println(Thread.currentThread().getName()+" -> "+container3.get());
            }, "consumer " + i).start();
        }

        //生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++)
                    container3.put(Thread.currentThread().getName() + " -> " + j);
            }, "producer " + i).start();
        }
    }

}
