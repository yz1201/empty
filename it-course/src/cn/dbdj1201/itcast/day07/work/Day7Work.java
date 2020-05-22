package cn.dbdj1201.itcast.day07.work;

import java.util.Arrays;
import java.util.Random;

/**
 * @author tyz1201
 * @datetime 2020-05-22 13:49
 **/
public class Day7Work {
    public static void main(String[] args) {
//        question1();
//        question2();
//        question3();
//        question4();
//        question5();
    }

    /*创建一个长度为6的整数数组，数组中有六个整数(直接赋值即可)。遍历数组中的每个元素，元素之间用空格隔开*/
    private static void question1() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    /*现有一个小数数组{12.9,53.54,75.0,99.1,3.14}。请编写代码，找出数组中的最小值并打印。*/
    private static void question2() {
        double[] arr = {12.9, 53.54, 75.0, 99.1, 3.14};
        System.out.println("min: " + Arrays.stream(arr).min().getAsDouble());
    }

    /*创建一个长度为6的整数数组。请编写代码，随机生成六个0-100之间的整数存放到数组中，然后再计算出数组中元素的和并打印。*/
    private static void question3() {
        int[] arr = new int[6];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100) + 1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.stream(arr).sum());
    }

    /*定义一个数组来存储10个学生的成绩{72,89,65,87,91,82,71,93,76,68}，计算并输出学生的平均成绩。*/
    private static void question4() {
        int[] scores = {72, 89, 65, 87, 91, 82, 71, 93, 76, 68};
        int sum = Arrays.stream(scores).sum();
        System.out.println("average: " + (double) sum / scores.length);
    }

    /*定义一个数组其中包含多个数字。用自己的方式最终实现，奇数放在数组的左边，偶数放在数组的右边。（可以创建其他数组，不必须在原数组中改变）*/
    private static void question5() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        for (int i : arr) {
            if (i % 2 != 0)
                result[left++] = i;
            else
                result[right--] = i;
        }
        System.out.println(Arrays.toString(result));
    }
}
