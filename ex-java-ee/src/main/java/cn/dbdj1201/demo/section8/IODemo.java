package cn.dbdj1201.demo.section8;

import java.io.*;

/**
 * @author tyz1201
 * @datetime 2020-05-22 10:29
 **/
public class IODemo {
    private static final String FILE_URL = "D:\\test\\test1\\t1.txt";

    public static void main(String[] args) throws IOException {
//        test3();
//        File srcFile = new File("D:\\test");
//        File destFile = new File("D:\\test21124");
        // 注意看C C步骤要回到C步骤，属于递归，得写个复制文件夹的功能
//        copyFolder(srcFile, destFile);
        test2();
    }

    private static void test1() {
        OutputStream os = null;
        String words = "hello java";
        try {
            os = new FileOutputStream("D:\\test\\test1\\t1.txt");
            os.write(words.getBytes());
            os.write(97);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void test2() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FILE_URL);
            byte[] bs = new byte[1024];
            int len;
            int by;
//            while ((by = fis.read()) != -1)
//                System.out.print((char) by);

            System.out.println("?");
            while ((len = fis.read(bs)) != -1) {
                System.out.println(new String(bs, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void test3() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("D:\\test\\test1\\pic1.png");
            fos = new FileOutputStream("D:\\test\\test1\\pic2.png");
            byte[] bs = new byte[1024];
            int len;
            while ((len = fis.read(bs)) != -1)
                fos.write(bs, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void copyFolder(File srcFile, File destFile) throws IOException {
        if (srcFile.isDirectory()) {
            // 是文件夹，就得先得到文件夹的路径
            File newFolder = new File(destFile, srcFile.getName());
            // 创建文件夹
            newFolder.mkdirs();
            File[] fileArray = srcFile.listFiles();
            for (File file : fileArray) {
                copyFolder(file, newFolder);
            }
        } else {
            // 是文件，先得到文件的路径，然后进行复制
            File newFile = new File(destFile, srcFile.getName());
            copyFile(srcFile, newFile);
        }
    }

    private static void copyFile(File srcFile, File newFile) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos =new FileOutputStream(newFile);
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read()) != -1) {
            fos.write(bys, 0, len);
        }
        fis.close();
        fos.close();
    }
}
