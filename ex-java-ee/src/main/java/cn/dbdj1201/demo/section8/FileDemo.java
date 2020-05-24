package cn.dbdj1201.demo.section8;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-22 9:21
 **/
public class FileDemo {
    private static final String FILE_URL = "D:\\test";

    public static void main(String[] args) {
//        testCreate();
        printAllFiles(new File(FILE_URL + "\\test1\\test4"));
    }

    private static void testDefinate() {
        File file = new File("D:\\test\\sparsearray\\testFile.txt");
        System.out.println(file);

        File file1 = new File("D:\\test\\sparsearray\\");
        File file2 = new File(file1, "test2.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(file2.compareTo(file));
    }

    private static void testCreate() {
        File file = new File(FILE_URL + "test");
//        try {
//            System.out.println(file.mkdir());
//            System.out.println(new File(file,"test1.txt").createNewFile());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file1 = new File(file, "test1.txt");

//        System.out.println(file1.getAbsoluteFile());
//        System.out.println(file1.getAbsolutePath());
//        System.out.println(file1.getPath());
//        try {
//            System.out.println(file1.getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        for (File s : file.listFiles()) {
//            System.out.println(s);
//            if (s.isDirectory())
//                s.delete();
//        }
    }

    private static void printAllFiles(File file) {
//        File file = new File(FILE_URL+"\\test1");
        File[] files = file.listFiles();

//        Arrays.stream(files).forEach(file1 -> {
//            if (file1.exists()) {
//                if (file1.isFile())
//                    System.out.println(file1);
//                else
//                    printAllFiles(file);
//            } else {
//                return;
//            }
//        });

//        if (files != null) {
//            Arrays.stream(files).forEach(file1 -> {
//                if (file1.isDirectory()) {
//                    System.out.println("dir: " + file1);
//                    printAllFiles(file1);
//                } else
//                    System.out.println(file1);
//            });
//
//        }
        System.out.println(Arrays.toString(files));

        Arrays.stream(files).forEach(file1 -> {
            if (file1.isDirectory()) {
                System.out.println("dir: " + file1);
                printAllFiles(file1);
            } else
                System.out.println(file1);
        });

    }
}
