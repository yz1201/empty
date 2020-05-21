package cn.dbdj1201.itcast.day06.work;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-21 13:06
 **/
public class Day6Work {
    public static void main(String[] args) {
//        question1();
//        question2();
//        question3();
//        question4();
    }

    /*
    有一个输出语句System.out.print("@")。使用这个语句，在控制台打印出一个四行五列的长方形，效果如下：
    @@@@@
	@@@@@
	@@@@@
	@@@@@
    */
    private static void question1() {
        for (int i = 0; i < 4; i++) {
            print(5);
            System.out.println();
        }
    }

    /*
    有一个输出语句System.out.print("@")。使用这个语句，在控制台打印出一个五行的三角形，效果如下：
        @
        @@
        @@@
        @@@@
        @@@@@
    */
    private static void question2() {
        for (int i = 1; i < 6; i++) {
            print(i);
            System.out.println();
        }
    }

    /**
     * @param i 打印i次@
     */
    private static void print(int i) {
        for (int k = 0; k < i; k++)
            System.out.print("@");
    }

    /*
    使用for循环打印出九九乘法表
    */
    private static void question3() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " x " + j + " = " + i * j + "   ");
            }
            System.out.println();
        }
    }

    /*
    程序开始随机产生1-100之间的随机数，随后使用键盘录入的数值和产生的随机数进行比对，如果没猜中，给出提示大了或小了，猜中了提示恭喜。
    */
    private static void question4() {
        int num = new Random().nextInt(100) + 1;
        while (true) {
            System.out.println("请输入猜测数字：");
            int guessNum = new Scanner(System.in).nextInt();
            if (guessNum < num)
                System.out.println("小了");
            else if (guessNum > num)
                System.out.println("大了");
            else {
                System.out.println("恭喜");
                break;
            }
        }
    }

}
