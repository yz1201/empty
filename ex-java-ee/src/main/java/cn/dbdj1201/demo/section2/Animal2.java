package cn.dbdj1201.demo.section2;

/**
 * @author tyz1201
 * @datetime 2020-05-21 3:30
 **/
public class Animal2 {

    private String name;
    private int age;

    public Animal2() {
    }

    public Animal2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void eat() {
        System.out.println("动物吃东西");
    }
}
