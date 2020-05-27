package cn.dbdj1201.iconcurrent.cap4;

/**
 * @author tyz1201
 * @datetime 2020-05-27 11:32
 **/
public class Test052701 {
    static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("?");
    }
}
