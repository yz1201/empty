package cn.dbdj1201.demo.section4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tyz1201
 * @datetime 2020-05-21 10:56
 **/
public class Section4 {
    public static void main(String[] args) {
        String str = "71 57 45 32 68 12";
        String[] strArr = str.split(" ");
        List<Integer> collect = Stream.of(strArr).map(Integer::valueOf).sorted().collect(Collectors.toList());
//        Stream.of(strArr).map(Integer::valueOf).sorted().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println(collect);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR) + " " + (calendar.get(Calendar.MONTH) + 1) + " " + calendar.get(Calendar.DATE));

        calendar.set(2050, Calendar.FEBRUARY, 31);
        System.out.println(calendar);

        calendar.set(2019, Calendar.MARCH, 1);
        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.get(Calendar.DATE));
//        try {
//            show();
//        } catch (IException e) {
//            e.printStackTrace();
//        }
        int i = testShow(-1);
        System.out.println("====="+i);

        try {
            System.out.println(new SimpleDateFormat("y").parse("---000020#$^#^#$6"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static int getDays(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.MARCH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    private static void show(int num) throws IException {
        if (num > 0)
            throw new IException("犯病了");

        System.out.println("he he ");
    }

    private static int testShow(int num) {
        try {
            show(num);
            num += 20;
            return num;
        } catch (IException e) {
            e.printStackTrace();
            return -3;
        } finally {
            System.out.println("进来了");
//            return ++num;
        }
    }
}


class IException extends Exception {

    public IException() {
    }

    public IException(String message) {
        super(message);
    }
}