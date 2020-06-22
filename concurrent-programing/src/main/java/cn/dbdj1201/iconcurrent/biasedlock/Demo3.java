package cn.dbdj1201.iconcurrent.biasedlock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @author tyz1201
 * @datetime 2020-06-18 22:31
 **/
public class Demo3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String url = "F:\\test\\test3\\person.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(url));
        Person3 p = new Person3("ysj", 12, 1, "ooooo");
        oos.writeObject(p);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(url));
        Object object = ois.readObject();
        System.out.println(object);
        oos.close();
        ois.close();
//        InputStream    Reader
//        OutputStream   Writer
//        Reader
//        Writer
        /*

        输入流   InputStream
        输出流   OutputStream
        字节 InputStream OutputStream
        字符 Reader Writer
        缓冲流
        */
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person3 implements Serializable {
    private String name;
    private int age;
    private transient int version;
    private String text;
    private static final long serialVersionUID = 1L;
}
