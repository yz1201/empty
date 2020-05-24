package cn.dbdj1201.demo;

import java.util.function.Predicate;

/**
 * @author tyz1201
 * @datetime 2020-05-24 18:33
 **/
public class TestPredicate {
    public static void main(String[] args) {
        boolean b1 = checkString("hello", s -> s.length() > 8);
        System.out.println(b1);

        boolean b2 = checkString("helloworld",s -> s.length() > 8);
        System.out.println(b2);

    }

    //判断给定的字符串是否满足要求
    private static boolean checkString(String s, Predicate<String> pre) {
//        return !pre.test(s);
        return pre.negate().test(s);
    }
}
