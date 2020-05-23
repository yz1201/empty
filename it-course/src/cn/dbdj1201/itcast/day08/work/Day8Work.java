package cn.dbdj1201.itcast.day08.work;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author tyz1201
 * @datetime 2020-05-23 13:40
 **/
public class Day8Work<T> {
    public static void main(String[] args) {
//        System.out.println(question1(3.14, 6.25));
//        System.out.println(question2(1, 1, 1, 1));
//        question3();
        System.out.println(question4(3.14));
        System.out.println(question4(-3.14));
    }

    /*定义一个方法，该方法能够找出两个小数中的较小值并返回。在主方法中调用方法进行测试。*/
    private static double question1(double var1, double var2) {
        return Math.min(var1, var2);
    }

    /*定义一个方法，该方法能够找出三个整数中的最大值并返回。在主方法中调用方法测试执行。*/
    private static int question2(int... nums) {
        OptionalInt min = Arrays.stream(nums).min();
        if (min.isPresent())
            return min.getAsInt();
        return -1;
    }

    /*通过键盘录入两个整数n和m。n代表行数，m代表列数。定义一个方法，方法的功能是打印n行m列的@符号。*/
    private static void question3() {
        System.out.println("请依次输入行数，列数");
        Scanner scanner = new Scanner(System.in);
        int col = scanner.nextInt();
        int row = scanner.nextInt();
        print(col, row);
    }

    private static void print(int col, int row) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++)
                System.out.print("@");
            System.out.println();
        }
    }

    /*数字是有绝对值的，负数的绝对值是它本身取反，非负数的绝对值是它本身。请定义一个方法，方法能够得到小数类型数字的绝对值并返回。请定义方法并测试*/
    private static double question4(double var) {
        return Math.abs(var);
    }
}
