package cn.dbdj1201.demo;

/**
 * @author tyz1201
 * @datetime 2020-05-24 21:53
 **/
public class Test258 {
    void test() {
        System.out.println("haha");
    }
}

class Crazy {


    public static void main(String[] args) {
        System.out.println("????");
        new Test258().test();

//        float var = 5 - 3;
//        switch (var) {
//            case 1:
//                System.out.println("?");
//                break;
//            case 2:
//                System.out.println();
//            default:
//                System.out.println("??");
//                break;
//        }
//        final int num = 5;
//        num =6;

        int count = 0;
        over:
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 250; j++) {
                System.out.println(i * j);
                if (i * j % 2 != 0) {
                    break over;
                }
                System.out.println("count-> " + count++);
                System.out.println();
            }
        }

        System.out.println("?????jiuzhe?");

    }
}
