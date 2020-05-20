package cn.dbdj1201.demo.section2;

/**
 * @author tyz1201
 * @datetime 2020-05-21 3:32
 **/
public class Dog2 extends Animal2 {

    public Dog2() {
    }

    public Dog2(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("dog eat meat");
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
