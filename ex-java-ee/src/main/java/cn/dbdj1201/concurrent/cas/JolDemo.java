package cn.dbdj1201.concurrent.cas;

import org.openjdk.jol.info.ClassLayout;

import java.lang.ref.SoftReference;

/**
 * @author tyz1201
 * @datetime 2020-04-13 18:01
 * Java object Layout Demo
 **/
public class JolDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }

}
