package cn.dbdj1201.demo.section2;

/**
 * @author tyz1201
 * @datetime 2020-05-21 4:13
 **/
public class TestA3 implements A3{
    @Override
    public void test() {
        class  Text{
            void method(){
                System.out.println("???");
            }
        }

        new Text().method();
    }

    @Override
    public void test1() {

    }

    public static void main(String[] args) {
        new TestA3().test();
        System.out.println(Math.ceil(4.5));
        System.out.println(Math.floor(4.5));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
