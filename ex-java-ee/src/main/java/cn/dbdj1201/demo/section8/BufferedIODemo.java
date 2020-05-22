package cn.dbdj1201.demo.section8;

import java.io.*;

/**
 * @author tyz1201
 * @datetime 2020-05-22 11:18
 **/
public class BufferedIODemo {
    private static final String FILE_NAME = "D:\\test\\test1\\t1.txt";

    public static void main(String[] args) throws IOException {
//        String str = "hello world";
//        byte[] bytes = str.getBytes();
//        String s = new String(bytes, StandardCharsets.UTF_8);
//        System.out.println(s);
//        copyFile2();


        FileReader fileReader = new FileReader(FILE_NAME);
        BufferedReader br = new BufferedReader(fileReader);
//        int len;
//        while ((len = fileReader.read()) != -1)
//            System.out.println(Integer.toBinaryString(len));
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);
    }

    private static void copyFile() {
        System.out.println("copy");
        FileWriter fw = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILE_NAME);
            fw = new FileWriter("D:\\test\\test1\\t12414.txt");
            int len;
            char[] cs = new char[1024];
            while ((len = fr.read(cs)) != -1)
                fw.write(cs, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyFile2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\test\\test1\\t12we414.txt"));

        try (br; bw) {
            String line;
//            char[] cs = new char[1024];
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
