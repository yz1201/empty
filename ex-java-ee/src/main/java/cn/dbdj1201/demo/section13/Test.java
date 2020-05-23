package cn.dbdj1201.demo.section13;

/**
 * @author tyz1201
 * @datetime 2020-05-23 9:59
 **/
public interface Test {
    default void test1() {
        test3();
        System.out.println("t1");
        test2();
    }

    void test2();

    private static void test3() {
        System.out.println("? t3");
    }

    static void test4() {
        System.out.println("t4");
    }
}
