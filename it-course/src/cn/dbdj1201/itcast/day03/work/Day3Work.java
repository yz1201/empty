package cn.dbdj1201.itcast.day03.work;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author tyz1201
 * @datetime 2020-05-16 14:11
 **/
public class Day3Work {

    public static void main(String[] args) {
//        work1();
//        work2();
//        work3();
    }

    /*
        红茶妹妹有21元钱，她攒了几天钱之后自己的钱比原来的两倍还多三块。绿茶妹妹有24元钱，
    她攒了几天钱之后自己的钱正好是原来的两倍。那么红茶和绿茶现在的钱一样多，请问对么？
    */
    private static void work1() {
        int money1 = 21;
        int nowMoney1 = money1 * 2 + 3;

        int money2 = 24;
        int nowMoney2 = 2 * money2;

        System.out.println("nowMoney1 == nowMoney2 -> " + (nowMoney1 == nowMoney2));
    }


    //编写程序，读入圆柱体的半径和高，并使用下列公式计算圆柱的体积： 
    private static void work2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入圆柱信息：");
        System.out.println("=============================================");
        System.out.println("请输入半径：");
        String radiusStr = scanner.nextLine();//第一次输入圆柱半径
        System.out.println("请输入高度：");
        String heightStr = scanner.nextLine();//第二次输入圆柱高度
        System.out.println("圆柱体积为：" + calVolume(radiusStr, heightStr));
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("OVER");
        scanner.close();
    }

    /*
     计算圆柱体积
         面积 = 半径 × 半径 × 3.14
         体积 = 面积 × 高 
     */

    /**
     * @param radiusStr 输入的圆柱体半径 字符串
     * @param heightStr 输入的圆柱体高度
     * @return 圆柱体体积，四舍五入，保留两位有效数字
     */
    private static double calVolume(String radiusStr, String heightStr) {
        BigDecimal radius = new BigDecimal(radiusStr);
        BigDecimal height = new BigDecimal(heightStr);

        return radius.multiply(radius).multiply(BigDecimal.valueOf(Math.PI)).multiply(height)
                .setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    /*
      已知三个同学的年龄分别为21岁、20岁、22岁，请用程序实现获取这三个同学的最大年龄。年龄数据由键盘录入。
    */
    private static void work3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请依次输入三人年龄：");
        int firstAge = scanner.nextInt();
        int secondAge = scanner.nextInt();
        int thirdAge = scanner.nextInt();
        System.out.println("这三人中年龄最大为" + Math.max(firstAge, Math.max(secondAge, thirdAge)) + "岁");
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GG");
        scanner.close();
    }


}

