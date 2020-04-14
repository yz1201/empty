package cn.dbdj1201.concurrent.cas;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author tyz1201
 * @datetime 2020-04-13 18:01
 * Java object Layout Demo
 **/
public class JolDemo {

    public static void main(String[] args) {
        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        StringBuffer stringBuffer;
        LinkedTransferQueue queue;
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }

}
