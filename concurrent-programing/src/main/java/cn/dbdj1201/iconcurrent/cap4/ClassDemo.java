package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-26 22:34
 **/
@Slf4j(topic = "c.ClassDemo")
public class ClassDemo {

    public static void main(String[] args) throws InterruptedException {
//        Integer integer = 0;
//        TimeUnit.SECONDS.sleep(2);
//        Object o = new Object();
//        System.out.println(o.hashCode());
        Dog o = new Dog();

        System.out.println(Integer.toBinaryString(o.hashCode()));
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }
}

class Dog {
    boolean flag = false;
}
