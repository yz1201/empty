package cn.dbdj1201.demo.section14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tyz1201
 * @datetime 2020-05-23 15:10
 **/
public class StreamDemo2 {
    /*
        需求：
            1.只使用前7个元素；
            2.跳过第一个元素；
            3.只留下女的；
            4.只留下年龄小于33的；
            5.每个字符串映射成一个User对象；
            6.把数据收集到集合中；
            7.遍历集合，输出内容；
	*/
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("柳岩,女,18");
        list.add("柳传志,男,21");
        list.add("关晓彤,女,19");
        list.add("凤姐,女,22");
        list.add("鹿晗,男,25");
        list.add("李若彤,女,33");
        list.add("闫妮,女,44");
        list.add("吴孟达,男,55");
        List<User> users = list.stream().limit(7).skip(1)
                .filter(s -> s.split(",")[1].equals("女"))
                .filter(s -> Integer.parseInt(s.split(",")[2]) < 33)
                .map(User::new).collect(Collectors.toList());
        System.out.println(users);

    }
}

class User {
    private String name;
    private String gender;
    private int age;

    public User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public User() {
    }

    public User(String s) {
        String[] split = s.split(",");
        this.name = split[0];
        this.gender = split[1];
        this.age = Integer.parseInt(split[2]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}