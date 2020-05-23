package cn.dbdj1201.demo.section13;

/**
 * @author tyz1201
 * @datetime 2020-05-23 9:58
 **/
public class InterfaceDemo implements Test {

    public static void main(String[] args) {
        new InterfaceDemo().test1();
        Test.test4();
        GoodSon.test4();
    }

    @Override
    public void test2() {
        System.out.println("son t2");
    }

    @Override
    public void test1() {
        System.out.println("son t1");
    }

    static void test4() {
        System.out.println("?");
    }

}

class GoodSon extends InterfaceDemo {

    public static void test4() {
        System.out.println("???");
    }
}