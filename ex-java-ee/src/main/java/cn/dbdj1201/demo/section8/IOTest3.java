package cn.dbdj1201.demo.section8;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author tyz1201
 * @datetime 2020-05-22 16:22
 **/
public class IOTest3 {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();
//        pro.setProperty("1","a");
//        pro.setProperty("1","b");
//        pro.setProperty("2","b");
//        pro.setProperty("3","c");
//        pro.store(new FileWriter("D:\\test\\test1\\pro.txt"), "gan ma yong de");
        pro.load(new FileReader("D:\\test\\test1\\pro.txt"));
        pro.entrySet().forEach(System.out::println);
    }
}
