package cn.dbdj1201.concurrent.cas;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author tyz1201
 * @datetime 2020-04-13 18:01
 * Java object Layout Demo
 **/
public class JolDemo {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
    }

}
