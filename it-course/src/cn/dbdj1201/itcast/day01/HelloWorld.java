package cn.dbdj1201.itcast.day01;

/**
 * @author tyz1201
 * @datetime 2020-05-14 12:43
 **/
public class HelloWorld {
    public static void main(String[] args) {
        work3();
        work4();
        work5();
    }

    //作业3 打印
    public static void work3() {
        System.out.println("java是一门跨平台的计算机语言\n" +
                "被称为一次编写，处处运行");
    }

    //作业4
    public static void work4() {
        //变量定义
        int val1 = -123;
        int val2 = 123;
        float val3 = 1.23f;
        double val4 = 12.3d;

        boolean flag1 = false;
        boolean flag2 = true;

        String str = "test string";
        char c = 't';

        //打印在控制台
        System.out.println(val1);
        System.out.println(val2);
        System.out.println(val3);
        System.out.println(val4);
        System.out.println(flag1);
        System.out.println(flag2);
        System.out.println(str);
        System.out.println(c);
    }

    //作业5
    public static void work5() {
        Person kobe = new Person("科比", 'm', 41, 1.98f, true);
        System.out.println(kobe);
    }
}

class Person {
    private String name;//姓名
    private char gender;//性别，m-male f-female
    private int age;//年龄
    private float height;//身高
    private boolean maritalStatus; //婚姻状况 true已婚，false未婚

    public Person(String name, char gender, int age, float height, boolean maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.maritalStatus = maritalStatus;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMarritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", height=" + height +
                ", maritalStatus=" + maritalStatus +
                '}';
    }
}