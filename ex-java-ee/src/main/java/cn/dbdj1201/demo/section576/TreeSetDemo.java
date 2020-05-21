package cn.dbdj1201.demo.section576;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author tyz1201
 * @datetime 2020-05-21 21:50
 **/
public class TreeSetDemo<T> implements Test<T>{
    public static void main(String[] args) {
        Set<Person1> s = new TreeSet<>();
        s.add(new Person1("t1", 1));
        System.out.println(s.add(new Person1("t2", 1)));
        s.add(new Person1("t3", 2));
        s.add(new Person1("t4", 3));
        s.add(new Person1("t4", 5));
        s.add(new Person1("t4", 3));
        System.out.println(s.size());
        System.out.println(s);

        Set<Person2> set = new TreeSet<>((p1, p2) -> p1.getAge() - p2.getAge() == 0 ? p1.getName().compareTo(p2.getName()) : p1.getAge() - p2.getAge());
        set.add(new Person2("p1", 1));
        set.add(new Person2("p2", 1));
        set.add(new Person2("p3", 2));
        set.add(new Person2("p4", 3));
        set.add(new Person2("p4", 3));
        System.out.println(set);

        for (Person1 person1 : s) {
            person1.show("?");
            person1.show(1);
        }

        new TreeSetDemo<String>().show("aa");
    }



    @Override
    public void show(T t) {
        System.out.println(t);
    }
}

class Person1 implements Comparable<Person1> {
    private String name;
    private int age;

    public Person1() {
    }

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public <T> void show(T t) {
        System.out.println(t);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person1 person1 = (Person1) o;
        return age == person1.age &&
                name.equals(person1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person1 o) {
        return this.age - o.age == 0 ? this.name.compareTo(o.name) : this.age - o.age;
    }
}

class Person2 {
    private String name;
    private int age;

    public Person2(String name, int age) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return age == person2.age &&
                Objects.equals(name, person2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

interface Test<T> {
    void show(T t);
}