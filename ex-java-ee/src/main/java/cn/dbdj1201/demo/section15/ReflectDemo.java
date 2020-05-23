package cn.dbdj1201.demo.section15;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-23 16:06
 **/
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        test2();
    }

    void test1() throws ClassNotFoundException {
        //使用类的class属性来获取该类对应的Class对象
        Class<Object> c1 = Object.class;
        System.out.println("-> " + c1);
        Class<Object> c2 = Object.class;
        System.out.println(c1 == c2);
        System.out.println("--------");

        //调用对象的getClass()方法，返回该对象所属类对应的Class对象
        Object s = new Object();
        Class<?> c3 = s.getClass();
        System.out.println(c1 == c3);
        System.out.println("--------");

        //使用Class类中的静态方法forName(String className)
        Class<?> c4 = Class.forName("java.lang.Object");
        System.out.println(c1 == c4);
    }

    private static void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> aClass = Class.forName("cn.dbdj1201.demo.section15.TestSonModel");
        System.out.println(aClass.getName());
//        Arrays.stream(aClass.getMethods()).forEach(System.out::println);
        Arrays.stream(aClass.getConstructors()).forEach(System.out::println);
        System.out.println("====");
        Arrays.stream(aClass.getFields()).forEach(System.out::println);
        System.out.println("====");
//        Arrays.stream(aClass.getDeclaredMethods()).forEach(System.out::println);
        Arrays.stream(aClass.getDeclaredConstructors()).forEach(System.out::println);
        System.out.println("====");
        Arrays.stream(aClass.getDeclaredFields()).forEach(System.out::println);

        Constructor<?> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o = constructor.newInstance();
        System.out.println(o);
        aClass.getDeclaredField("herAge").set(o, 235);
        Field testProperty = aClass.getDeclaredField("testProperty");
        testProperty.setAccessible(true);
        testProperty.set(o, "????");
        System.out.println(o);
        Method testSon = aClass.getDeclaredMethod("testSon2", String.class);
        testSon.invoke(o, "hello hi");
        testSon = aClass.getDeclaredMethod("testSon");
        testSon.setAccessible(true);
        testSon.invoke(o);
    }
}

class TestModel {
    private String name;
    public int age;
    double money;
    protected String gender;
    public String test;

    public TestModel(String name, int age, double money, String gender) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.gender = gender;
    }

    public TestModel() {
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private static void show() {
        System.out.println("just for test");
    }

    public void show1(){
        System.out.println("just for test1");
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class TestSonModel extends TestModel {

    private String testProperty;
    int herAge;
    protected double poor;
    public boolean flag;

    public TestSonModel(String testProperty) {
        this.testProperty = testProperty;
    }

    private TestSonModel() {
        this.testProperty = "55";
    }

    @Override
    public String toString() {
        return "TestSonModel{" +
                "testProperty='" + testProperty + '\'' +
                ", herAge=" + herAge +
                ", poor=" + poor +
                ", flag=" + flag +
                '}';
    }

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }

    protected void testSon2(String words) {
        System.out.println(words);
    }

    private void testSon() {
        System.out.println("i just smoke");
    }
}