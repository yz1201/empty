package cn.dbdj1201.demo;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-20 22:08
 **/
public class Test1 {
    public static void main(String[] args) {
//        K2 k2 = new K2();
//        System.out.println(Integer.toHexString(k2.hashCode()));
//        k2.show();
//        System.out.println("===============");
//        new K1().show();
//        k2.setName("la la");
//        System.out.println(k2.getName());
//        k2.show();
        K3 k3 = new K3();
        k3.setName("asd");
//        System.out.println(k3.getName());
//
//        Arrays.stream(k3.getClass().getDeclaredFields()).forEach(System.out::println);
//        Arrays.stream(k3.getClass().getFields()).forEach(System.out::println);
//        K1.method1();
//        K3.method1();
//        k3.method1();
//        K1 k1 = new K3();
//        k1.method1();
//
//        k1.show();
//        k3.show();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//            }
//        };

        String message="我是一个出色的IT讲师";

        String finalMessage = message;
        A a = new A() {
            @Override
            public void show() {
                System.out.println(finalMessage);
            }
        };
        message="不，我不是很合格";
        a.show();
    }

    static class Test2 {

        void show() {
            System.out.println("i am test2");
        }
    }

    class Test068 {
        void show() {
            System.out.println("i am 068");
        }
    }
}

abstract class A{
    public abstract void show();
}
class K1 {
    private String name;
    int num = 10;
    public String testField;

    public K1(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public K1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "K1{" +
                "name='" + name + '\'' +
                '}';
    }

    public void show() {
//        System.out.println("wsnd");
//        System.out.println(this);
//        System.out.println(this.num);
//        System.out.println("wsnd");
        System.out.println("k1 show");
    }

    static void method1() {
        System.out.println("?");
    }
}

class K2 extends K1 {

    int num = 200;

    @Override
    public void show() {
        super.show();
        System.out.println(super.num);
        System.out.println(this);
        System.out.println(this.num);
    }

//    @Override
//    public String toString() {
//        return "K2{" +
//                "num=" + num +
//                '}';
//    }
}

class K3 extends K2 {

    static void method1() {
        System.out.println("???");
    }

    @Override
    public void show() {
        System.out.println("k3 show");
        super.getName();
//        System.out.println(super);
        System.out.println(super.getClass().getName());
    }
}
