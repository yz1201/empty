package cn.dbdj1201.demo.section576;

import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-05-21 14:31
 **/
public class ListDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("t-1", 1));
        students.add(new Student("t-2", 2));
        students.add(new Student("t-3", 3));
        students.add(new Student("t-4", 4));
        students.add(new Student("t-5", 5));
        students.add(new Student("t-6", 6));
        Iterator<Student> iterator = students.iterator();

        students.add(new Student());
        while (iterator.hasNext()) {
            Student next = iterator.next();
            if (next.getName().equals("t-3"))
                iterator.remove();
//                students.remove(next);
            System.out.println(next);
        }

//        System.out.println(students);

        ListIterator<Student> studentListIterator = students.listIterator();
        studentListIterator.forEachRemaining(System.out::println);
        for (Student student : students) {
//            if (student.getAge()==5)
//                students.add(new Student());
            System.out.println(student);
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
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
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}