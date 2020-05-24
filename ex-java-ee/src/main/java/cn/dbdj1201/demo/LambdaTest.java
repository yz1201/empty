package cn.dbdj1201.demo;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author tyz1201
 * @datetime 2020-05-24 10:20
 **/
public class LambdaTest {
    public static void main(String[] args) {
//        method(( )-> {
//
//        });
        method(new T1() {
            @Override
            public void test() {
                System.out.println("mmp");
            }
        });

        method1(() -> System.out.println("????"));
    }

    private static void method(T1 t1) {
        t1.test();
    }

    private static void method1(T2 t2) {
        t2.test();
    }
}

abstract class T1 {
    public abstract void test();
}

interface T2 {
    void test();
}