package cn.dbdj1201.demo.section14;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author tyz1201
 * @datetime 2020-05-23 10:55
 **/
public class SupTest {
    public static void main(String[] args) {
        System.out.println(getMax(() -> 1));
        Consumer consumer;
    }

    //返回一个int数组中的最大值
    private static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
