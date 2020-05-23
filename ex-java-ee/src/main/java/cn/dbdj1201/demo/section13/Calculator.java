package cn.dbdj1201.demo.section13;

/**
 * @author tyz1201
 * @datetime 2020-05-23 10:42
 **/
@FunctionalInterface
public interface Calculator {
    int add(int a, int b);

    boolean equals(Object obj);

    String toString();

    static void showInterface() {
        System.out.println("这是个计算器接口");
    }
}
