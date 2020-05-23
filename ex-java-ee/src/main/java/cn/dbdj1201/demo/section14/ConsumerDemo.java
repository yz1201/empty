package cn.dbdj1201.demo.section14;

import java.util.function.Consumer;

/**
 * @author tyz1201
 * @datetime 2020-05-23 11:00
 **/
public class ConsumerDemo {
    public static void main(String[] args) {
        //操作一
        operatorString("林青霞", System.out::println);
        //操作二
        operatorString("林青霞", s -> System.out.println(new StringBuilder(s).reverse().toString()));

        System.out.println("--------");
        //传入两个操作使用andThen完成
        operatorString("林青霞", System.out::println, s -> System.out.println(new StringBuilder(s).reverse().toString()));
    }

    //定义一个方法，用不同的方式消费同一个字符串数据两次
    private static void operatorString(String name, Consumer<String> con1, Consumer<String> con2) {
//        con1.accept(name);
//        con2.accept(name);
        con1.andThen(con2).accept(name);
    }

    //定义一个方法，消费一个字符串数据
    private static void operatorString(String name, Consumer<String> con) {
        con.accept(name);
    }
}
