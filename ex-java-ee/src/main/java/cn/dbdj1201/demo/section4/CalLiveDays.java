package cn.dbdj1201.demo.section4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tyz1201
 * @datetime 2020-05-21 12:04
 **/
public class CalLiveDays {
    public static void main(String[] args) {
//        System.out.println("输入出生日期");
//        calLiveDays(1994,9,23);
        String date = "---0002022220-05-20";
        calLiveDays(date);
    }

    private static void calLiveDays(String date) {
        try {
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Date now = new Date();
            long days = (now.getTime() - birthday.getTime()) / 1000 / 3600 / 24;
            System.out.println(" -> " + days);
        } catch (ParseException e) {
            System.out.println("出问题了");
            e.printStackTrace();
        }

    }

//    private static void calLiveDays(int year, int month, int day) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year,month-1,day);
//
//
//
//    }
}
