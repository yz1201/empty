package cn.dbdj1201.demo;

/**
 * @author tyz1201
 * @datetime 2020-05-20 22:08
 **/
public class Test1 {
    public static void main(String[] args) {
        K2 k2 = new K2();
//        System.out.println(Integer.toHexString(k2.hashCode()));
//        k2.show();
//        System.out.println("===============");
//        new K1().show();
        k2.setName("la la");
//        System.out.println(k2.getName());
        k2.show();
    }

    static class Test2 {

        void show() {
            System.out.println("i am test2");
        }
    }

    class Test068 {
        void show() {
            System.out.println("i am 068");
        }
    }
}

class K1 {
    private String name;
    int num = 10;

    public K1(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public K1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "K1{" +
                "name='" + name + '\'' +
                '}';
    }

    public void show() {
        System.out.println("wsnd");
        System.out.println(this);
        System.out.println(this.num);
        System.out.println("wsnd");
    }
}

class K2 extends K1 {

    int num = 200;

    @Override
    public void show() {
        super.show();
        System.out.println(super.num);
        System.out.println(this);
        System.out.println(this.num);
    }

//    @Override
//    public String toString() {
//        return "K2{" +
//                "num=" + num +
//                '}';
//    }
}
