package cn.dbdj1201.demo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tyz1201
 * @datetime 2020-05-24 10:39
 **/
public class SetDemo {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        set.add("1");
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        new Generic().test("c");
//        new Generic().test(true);
//        new Generic().test(1);
//        new Generic().test(2.35);
//
//        new T234<Integer>().test(564);
//        new T234<Integer>().test(564);
//        new T234<Integer>().test(564);
//        new T234<Integer>().test(564);
        List<Integer> integers = List.of(1, 2, 3, 4);
//        integers.add(5);
//        integers.clear();
//        integers.set(2, 3);
    }
}

class Generic {
    public <T> void test(T t) {
        System.out.println(t);
    }
}

class T234<T> {
    void test(T t) {
        System.out.println(t);
    }
}