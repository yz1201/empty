package cn.dbdj1201.demo.section576;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author tyz1201
 * @datetime 2020-05-21 22:53
 **/
public class Work2 {

    public static void main(String[] args) {
        Set<StudentTest<Integer>> set = new TreeSet<>();
        set.add(new StudentTest<>("s1",5));
        set.add(new StudentTest<>("s2",2));
        set.add(new StudentTest<>("s3",3));
        set.add(new StudentTest<>("s3",3));
        set.add(new StudentTest<>("s4",3));
        System.out.println(set);
    }
}

class StudentTest<T> implements Comparable<StudentTest> {
    private String name;
    private T age;

    public StudentTest(String name, T age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTest<?> that = (StudentTest<?>) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "StudentTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(StudentTest o) {
        int thisAge;
        int thatAge;
        if (!(this.age instanceof Integer)) {
            thisAge = Integer.parseInt((String) this.age);
            thatAge = Integer.parseInt((String) o.age);
        } else {
            thisAge = (int) this.age;
            thatAge = (int) o.age;
        }
        int num = thisAge - thatAge;
        return num == 0 ? this.name.compareTo(o.getName()) : num;
    }
}