package cn.dbdj1201.itcast.day09.work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * @author tyz1201
 * @datetime 2020-05-25 14:11
 **/
public class Day9Work {

    public static void main(String[] args) throws ParseException {
//        question1();
//        question2();
    }

    /*
        需求：
            企业发放的奖金根据利润提成。
                利润低于或等于10万元时，奖金可提10%；
                利润高于10万元，低于或等于20万元时，高于10万元的部分，可提成7.5%；
                高于20万，低于或等于40万时，高于20万元的部分，可提成5%；
                高于40万，低于或等于60万时，高于40万元的部分，可提成3%；
                高于60万，低于或等于100万时，高于60万元的部分，可提成1.5%；
                高于100万元时，超过100万元的部分按1%提成；
        从控制台录入当月利润，求应发放奖金总数，并输出到控制台
    */
    private static void question1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入利润：");
        int profit = sc.nextInt();
        double bonus;//奖金
        if (profit <= 100000) {
            bonus = profit * 0.1;
            System.out.println(bonus);
        } else if (profit > 100000 && profit <= 200000) {
            bonus = 100000 * 0.1 + 0.075 * (profit - 100000);
            System.out.println(bonus);
        } else if (profit > 200000 && profit <= 400000) {
            bonus = 100000 * 0.1 + 0.075 * 100000 + 0.05 * (profit - 200000);
            System.out.println(bonus);
        } else if (profit > 400000 && profit <= 600000) {
            bonus = 100000 * 0.1 + 0.075 * 100000 + 200000 * 0.05 + 0.03 * (profit - 400000);
            System.out.println(bonus);
        } else if (profit > 600000 && profit <= 1000000) {
            bonus = 100000 * 0.1 + 0.075 * 100000 + 200000 * 0.05 + 0.03 * 200000 + 0.015 * (profit - 600000);
            System.out.println(bonus);
        } else {
            bonus = 100000 * 0.1 + 0.075 * 100000 + 200000 * 0.05 + 0.03 * 200000 + 0.015 * 400000 + 0.01 * (profit - 1000000);
            System.out.println(bonus);
        }

    }

    /*
    需求：
		1.控制台提示用户输入年份（例如 2018）
		2.控制台提示用户输入月份（例如 11）
		3.计算用户输入的日期（2018-11-1）离1900年1月1日相距多少天。（注意闰年，每个月份天数不一样）
    */
    private static void question2() throws ParseException {
        //当前时间毫秒值-1900-01-01毫秒值，再换算成天数，完事。
        System.out.println("输入日期，格式形如：2020-05-25");
        String birthday = new Scanner(System.in).nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long birthTime = sdf.parse(birthday).getTime();
        long thatTime = sdf.parse("1900-01-01").getTime();
        System.out.println("ans: " + ((birthTime - thatTime) / 1000 / 3600 / 24 + 1));
    }
}

/**
 * 计算奖金工具类
 */
//class Profits {
//

//    private static double getBounus(double profit) {
//        BigDecimal profitDecimal = new BigDecimal(profit);
//        BigDecimal bonus = new BigDecimal(0);
//        BigDecimal tempVal10 = new BigDecimal("0.1").multiply(new BigDecimal("100000"));//十万部分奖金
//        BigDecimal tempVal20 = new BigDecimal("0.075").multiply(new BigDecimal("100000"));//二十万部分奖金
//        BigDecimal tempVal40 = new BigDecimal("0.05").multiply(new BigDecimal("200000"));//四十万部分奖金
//        BigDecimal tempVal60 = new BigDecimal("0.03").multiply(new BigDecimal("200000"));//六十万部分奖金
//        BigDecimal tempVal100 = new BigDecimal("0.015").multiply(new BigDecimal("400000"));//一百万部分奖金
//        BigDecimal val10 = new BigDecimal("100000");
//        BigDecimal val20 = new BigDecimal("200000");
//        BigDecimal val40 = new BigDecimal("400000");
////        BigDecimal val10 = new BigDecimal("600000");
////        BigDecimal val10 = new BigDecimal("1000000");
//        if (profit <= 100000) {
//            bonus = profitDecimal.multiply(new BigDecimal("0.1"));
//        } else if (profit <= 200000)
//            bonus = profitDecimal.subtract(val10).multiply(new BigDecimal("0.075")).add(tempVal10);
//        else if (profit <= 400000)
//            bonus = profitDecimal.subtract(val20).multiply(new BigDecimal("0.05")).add(tempVal20);
//        else if (profit<=600000)
//            bonus = profitDecimal.subtract(val40).multiply(new BigDecimal("0.03")).add(tempVal40);
//        return -1;
//    }


//}