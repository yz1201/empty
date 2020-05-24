package cn.dbdj1201.demo;

import java.util.Random;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-24 11:43
 **/
public class TestFinally {
    public static void main(String[] args) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
////            System.exit(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            System.out.println("????");
//        }

        for (int i = 1; i <= 9; i++) {    //1
            for (int j = 1; j <= i; j++) { //2
                System.out.print(j + "*" + i + "=" + (i * j) + "\t");//3
            }
            System.out.println();//4
        }


    }
}

@FunctionalInterface
interface Test12360 {
    void test();

    private boolean flag() {
        return false;
    }

    default void test2() {

    }
}