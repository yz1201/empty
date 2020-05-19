package cn.dbdj1201.iconcurrent.cap3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tyz1201
 * @datetime 2020-05-19 14:31
 **/
@Slf4j
public class TestFrames {
    public static void main(String[] args) {
        new Thread(() -> method(20), "t-1").start();
        method(10);
    }

    private static void method(int i) {
        int y = i + 1;
        Object m = method2();
        System.out.println(y + m.toString());
    }

    private static Object method2() {
        return new Object();
    }
}
