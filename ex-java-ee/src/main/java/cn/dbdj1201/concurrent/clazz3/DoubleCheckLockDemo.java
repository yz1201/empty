package cn.dbdj1201.concurrent.clazz3;

/**
 * @author tyz1201
 * @datetime 2020-04-08 18:39
 **/
public class DoubleCheckLockDemo {

    private static DoubleCheckLockDemo demo;
    private static final Object obj = new Object();

    public DoubleCheckLockDemo() {
        System.out.println("generate your data!");
    }

    public static DoubleCheckLockDemo getInstance() {

        synchronized (obj) {
            if (demo == null) {
                synchronized (obj) {
                    demo = new DoubleCheckLockDemo();
                }
            }
        }
        return demo;
    }

    public static DoubleCheckLockDemo getIns() {
        synchronized (obj) {
            if (demo == null) {
                demo = new DoubleCheckLockDemo();
            }
        }

        return demo;
    }

    private static class Inner {
        private static DoubleCheckLockDemo demo = new DoubleCheckLockDemo();
    }

    private static DoubleCheckLockDemo getDemo() {
        return Inner.demo;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(DoubleCheckLockDemo::getDemo);
            threads[i].start();
        }


    }
}
