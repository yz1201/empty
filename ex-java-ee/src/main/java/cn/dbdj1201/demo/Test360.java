package cn.dbdj1201.demo;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tyz1201
 * @datetime 2020-05-24 22:27
 **/
public class Test360 {
    public static void main(String[] args) {
//        TestF1 f1 = new TestF2();
//        int num = 1;
//        method(() -> {
//            System.out.println(num);
//            num = 250;
//        });
//        char c = 'c';
//        byte b = 'v';
//        c++;
//        System.out.println(c);
//        System.out.println(b);

//        Integer a = new Integer(3);
//        Integer b = 3;  // 将3自动装箱成Integer类型
//        int c = 3;
//        System.out.println(a == b); // false 两个引用没有引用同一对象
//        System.out.println(a == c); // true a自动拆箱成int类型再和c比较
//        System.out.println(b == c); // true

//        Integer a1 = 128;
//        Integer b1 = 128;
//        System.out.println(a1 == b1); // false
//
//        Integer a2 = 127;
//        Integer b2 = 127;
//        System.out.println(a2 == b2); // true
        int num = 5;
        System.out.println(num & 4);
        System.out.println(num % 5);
    }

    static void method(Test369 test369) {
        test369.test();
    }
}

class TestF1 {
    void test() {
        System.out.println("baby dont high");
    }
}

class TestF2 extends TestF1 {
    void test1() {
        System.out.println("???");
    }
}

interface Test369 {
    void test();
}