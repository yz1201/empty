package cn.dbdj1201.iconcurrent.cap4;

import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-26 10:22
 **/
public class SynchronizedDemo {
    public static void main(String[] args) {
        Room room = new Room();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                room.increment();
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                room.decrement();
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(room.getCounter());
    }

}

class Room {
    private int counter = 0;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    /*保证获取最终结果*/
    public int getCounter() {
//        synchronized (this) {
//            return counter;
//        }
        return counter;
    }

}
