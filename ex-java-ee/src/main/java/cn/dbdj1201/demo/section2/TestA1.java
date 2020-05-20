package cn.dbdj1201.demo.section2;

/**
 * @author tyz1201
 * @datetime 2020-05-21 4:58
 **/
public class TestA1 {

    public static void main(String[] args) {
        int num = 1;
        TestA123 testA123 = new TestA123() {
            @Override
            public void show() {
                System.out.println(num);
            }
        };

//        num = 25;
    }
}

abstract class TestA123 {
    public abstract void show();
}
