package cn.dbdj1201.demo.section2;

/**
 * @author tyz1201
 * @datetime 2020-05-21 3:20
 **/
public class Test1 {

    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println(cat.age);
        cat.eat();
    }
}

class Animal {
    int age = 40;

    public void eat() {
        System.out.println("animal eat");
    }
}

class Cat extends Animal {

    int age = 10;
    int weight = 15;

    @Override
    public void eat() {
        System.out.println("cat eat");
    }

    public void playGame() {
        System.out.println("wan mao xian");
    }
}