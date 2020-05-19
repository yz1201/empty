package cn.dbdj1201.itcast.day05.work;

/**
 * @author tyz1201
 * @datetime 2020-05-19 12:31
 **/
public class Day5Work {
    public static void main(String[] args) {
//        question1();
//        question2();
//        question3();
    }

    /*
    已知2019年是猪年，请在控制台输出从1949年到2019年中所有是猪年的年份。
    */
    private static void question1() {
        int year = 2019;
        System.out.println("1949-2019，是猪年的年份如下：");
        while (year >= 1949) {
            System.out.println(year);
            year -= 12;
        }
    }

    /*
    中国有闰年的说法。闰年的规则是：四年一闰，百年不闰，四百年再闰。（年份能够被4整除但不能被100整除算是闰年，年份能被400整除也是闰年）。
    请打印出1988年到2019年的所有闰年年份。
    */
    private static void question2() {
        System.out.println("1988-2019年，闰年年份如下：");
        for (int i = 1988; i <= 2019; i++) {
            if (isLeapYear(i))
                System.out.println(i);
        }
    }

    /**
     * 判断该年份是否是闰年
     *
     * @param year
     * @return true->闰年
     */
    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    /*有一个容量为10L的空水桶。水桶的上面开始往里灌水，同时下面开始往出流水。
        第一分钟灌水的速度是1L/min，
        第二分钟灌水的速度是2L/min，
        第三分钟灌水的速度是3L/min，
    以此类推。而流水的速度固定是3L/min。那么几分钟之后，水桶里能保持灌满水的状态？
    */
    private static void question3() {
        int capacity = 10;  //水桶总容量
        //前三分钟，桶内没蓄水
        int time = 3;
        //从第4分钟开始，桶内注水量大于出水量，且每分钟比之前多注1升水
        int sum = 0;
        while (sum <= capacity) {
            sum += ++sum;
            time++;
        }
        System.out.println("第 " + time + "分钟后，水桶里能保持满水的状态");
    }


}
