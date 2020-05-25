package cn.dbdj1201.test;

import java.io.*;

/**
 * @author tyz1201
 * @datetime 2020-05-25 12:38
 **/
public class Test3 {
    public static void main(String[] args) throws IOException {
        //使用缓冲流将桌面上的 1.txt（内容自拟）拷贝到 2.txt
        //注：本例中使用文本文件 采用字符缓冲流
        String from = "C:\\Users\\Administrator\\Desktop\\t1.txt";
        String to = "C:\\Users\\Administrator\\Desktop\\t2.txt";
        BufferedReader br = new BufferedReader(new FileReader(from));
        BufferedWriter bw = new BufferedWriter(new FileWriter(to));

        try (br; bw) { //jdk9优化
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源释放
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
