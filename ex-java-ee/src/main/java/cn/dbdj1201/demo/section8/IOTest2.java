package cn.dbdj1201.demo.section8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-22 15:58
 **/
public class IOTest2 {
    public static void main(String[] args) throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\test\\test1\\obj.txt"));
//        StudentModel s1 = new StudentModel("test1", 1, 2, 3);
//        StudentModel s2 = new StudentModel("test1", 1, 2, 3);
//        StudentModel s3 = new StudentModel("test1", 1, 2, 3);
//        oos.writeObject(s1);
//        oos.writeObject(s2);
//        oos.writeObject(s3);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\test\\test1\\obj.txt"));
//        Object obj = null;
        List<StudentModel> studentModels = new ArrayList<>();
        while (true) {
            try {
                studentModels.add((StudentModel) ois.readObject());
            } catch (EOFException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println(studentModels);
        ois.close();
    }
}
