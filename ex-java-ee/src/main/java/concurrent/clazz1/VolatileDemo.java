package concurrent.clazz1;

/**
 * @author tyz1201
 * @datetime 2020-04-08 10:38
 **/
public class VolatileDemo {

    private static volatile int value = 0;


    synchronized void m() {
        for (int i = 0; i < 10000000L; i++) {
//            System.out.println(Thread.currentThread().getName() + " -> " + value++);
            value++;
        }
    }

    void m1() {
        long val = 0;
        while (val < 10000000L) {
            value++;
            val++;
        }

    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        Thread t1 = new Thread(demo::m, "t1");
        Thread t2 = new Thread(demo::m, "t2");
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println(value);

//        VolatileDemo demo = new VolatileDemo();
//        Thread t1 = new Thread(demo::m1);
//        Thread t2 = new Thread(demo::m1);
//        t1.start();
//        t2.start();
//        while (t1.isAlive() || t2.isAlive()) {
//
//        }
//        System.out.println("value -> " + value);
    }
}

