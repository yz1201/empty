package cn.dbdj1201.demo.section576;

import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-05-21 23:21
 **/
public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("t1", "12");
        map.put("t2", "14");
        map.put("t2", "15");
        map.put("t2", "16");
        map.put("t3", "17");
        System.out.println(map);
        map.entrySet().forEach(System.out::println);
        System.out.println(map.remove("t2"));

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(5);
        nums.add(3);
        System.out.println(nums);
        Collections.shuffle(nums);
        System.out.println(nums);
        nums.sort(Comparator.comparingInt(n -> n));

        List<Student369> list = new ArrayList<>();
        list.add(new Student369("s1", 12));
        list.add(new Student369("s2", 242));
        list.add(new Student369("s3", 132142));
        list.add(new Student369("s4", 12212));
        list.add(new Student369("s5", 512));
        list.sort((s1, s2) -> s2.getAge() - s1.getAge() == 0 ? s2.getName().compareTo(s1.getName()) : s2.getAge() - s1.getAge());
        System.out.println(list);

    }


}

class Student369 {
    private String name;
    private int age;

    public Student369(String name, int age) {
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
        Student369 that = (Student369) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student369{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
