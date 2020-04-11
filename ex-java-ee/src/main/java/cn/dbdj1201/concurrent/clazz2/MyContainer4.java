package cn.dbdj1201.concurrent.clazz2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tyz1201
 * @datetime 2020-04-08 17:19
 **/
public class MyContainer4<T> {
    private final LinkedList<T> list = new LinkedList<>();
    private final static int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }

            list.add(t);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }

            t = list.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer4<String> container3 = new MyContainer4<>();
        //消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++)
                    System.out.println(Thread.currentThread().getName() + " -> " + container3.get());
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
