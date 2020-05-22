package cn.dbdj1201.demo.section8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author tyz1201
 * @datetime 2020-05-22 14:26
 **/
public class IOTest {
    public static void main(String[] args) throws IOException {
        /*- 键盘录入5个学生信息(姓名,语文成绩,数学成绩,英语成绩)。要求按照成绩总分从高到低写入文本文件
        - 格式：姓名,语文成绩,数学成绩,英语成绩  举例：林青霞,98,99,100
        */

        Set<StudentModel> studentModels = new TreeSet<>((s1, s2) -> s2.sum() - s1.sum());
        studentModels.add(new StudentModel("t-1", 100, 20, 30));
        studentModels.add(new StudentModel("t-2", 20, 40, 30));
        studentModels.add(new StudentModel("t-3", 30, 60, 30));

        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\test\\test1\\scores.txt"));
        for (StudentModel studentModel : studentModels) {
            bw.write(studentModel.toString() + " -> sum: " + studentModel.sum());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
}

class StudentModel implements Serializable {
    private String name;
    private int chinese;
    private int math;
    private int english;

    public StudentModel() {
    }

    public StudentModel(String name, int chinese, int math, int english) {
        this.name = name;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int sum() {
        return this.chinese + this.math + this.english;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentModel that = (StudentModel) o;
        return chinese == that.chinese &&
                math == that.math &&
                english == that.english &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chinese, math, english);
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "name='" + name + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }
}
