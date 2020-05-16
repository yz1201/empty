package cn.dbdj1201.itcast.day02.work;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author tyz1201
 * @datetime 2020-05-15 12:37
 **/
public class Day2Work {

    public static void main(String[] args) {
        work1();
        work2();
        work3();
        work4();
    }

    private static void work1() {
        //整型
        byte val1 = 127;
        short val2 = 123;
        int val3 = 124;
        long val4 = 145L;
        //浮点型
        float f = 1.23f;
        double d = 23.5;

        boolean flag = true;
        boolean flag1 = false;

        //字符型
        char c = '?';
        //打印
        System.out.println("val1: " + val1);
        System.out.println("val2: " + val2);
        System.out.println("val3: " + val3);
        System.out.println("val4: " + val4);

        System.out.println(f);
        System.out.println(d);

        System.out.println(flag);
        System.out.println(flag1);

        System.out.println(c);
    }

    private static void work2() {
        int studentsNum = 100;
        float goodsPrice = 55.55F;
        long earthAge = 4600000000L;
        boolean flag = false;

        System.out.println(studentsNum);
        System.out.println(goodsPrice);
        System.out.println(earthAge);
        System.out.println(flag);
    }

    private static void work3() {
        int num1 = 10;
        int num2 = 20;
        int ans = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + ans);

    }

    private static void work4() {
        double fatherHeight = 1.77;
        double motherHeight = 1.65;

        System.out.println("son's height -> " + remainTwoEffectiveWords(getSonHeight(fatherHeight, motherHeight)) + "米");
        System.out.println("daughter's height -> " + remainTwoEffectiveWords(getDaughterHeight(fatherHeight, motherHeight)) + "米");
    }

    private static double getSonHeight(double fatherHeight, double motherHeight) {
        return (fatherHeight + motherHeight) * 1.08 / 2;
    }

    private static double getDaughterHeight(double fatherHeight, double motherHeight) {
        return (fatherHeight * 0.923 + motherHeight) / 2;
    }

    /**
     * 保留两位有效数字，四舍五入
     *
     * @param val
     * @return
     */
    private static double remainTwoEffectiveWords(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
