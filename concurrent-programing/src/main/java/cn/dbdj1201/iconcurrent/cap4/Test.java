package cn.dbdj1201.iconcurrent.cap4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author tyz1201
 * @datetime 2020-05-26 18:35
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Collection.class.getInterfaces()));
        System.out.println("=================");
        System.out.println(Arrays.toString(ArrayList.class.getInterfaces()));
    }
}
