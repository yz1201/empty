package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-05 13:59
 * 死锁Demo
 **/
public class DeathLockDemo {
    private volatile boolean flag = true;

    void m() {
        System.out.println(Thread.currentThread().getName() + " m start ");
        while (flag) {
            System.out.println(Thread.currentThread().getName() + " m running");
            //                TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + " m end ");
    }

    void m1() {
        System.out.println(Thread.currentThread().getName() + " i am modify your flag");
        this.flag = false;
    }

    public static void main(String[] args) {
//        ThreadLocal tl;
//        DeathLockDemo deathLockDemo = new DeathLockDemo();
//        new Thread(deathLockDemo::m, "thread-1").start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(deathLockDemo::m1, "thread-2").start();

        new Thread(new DeathLock(0)).start();
        new Thread(new DeathLock(1)).start();
    }

}

class DeathLock implements Runnable {
    private int value;
    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();

    public DeathLock(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        if (value == 0) {
            synchronized (obj1) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    for (int i = 11; i < 20; i++) {
                        System.out.println("o1 -> " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("o2 -> " + value);
                }
            }
        }

        if (value == 1) {
            synchronized (obj2) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    for (int i = 11; i < 20; i++) {
                        System.out.println("o2 -> " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println("o1 -> " + value);
                }
            }
        }
    }
}