package cn.dbdj1201.itcast.day04.work;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-18 12:32
 **/
public class Day4Work {

    public static void main(String[] args) {
//        question1();
//        question2();
//        question3();
//        question4();
    }

    /*
     李雷想买一个价值7988元的新手机，她的旧手机在二手市场能卖1500元，而手机专卖店推出以旧换新的优惠，把她的旧手机交给店家，
     新手机就能够打8折优惠。为了更省钱，李雷要不要以旧换新？请在控制台输出。
     */
    public static void question1() {
        double newPhone = 7988;//新手机价格
        double oldPhone = 1500;//现有二手机价格

        //折扣换算现金
        double discount = new BigDecimal(newPhone).subtract(new BigDecimal(newPhone)
                .multiply(new BigDecimal("0.8"))).doubleValue();

        if (discount > oldPhone)
            System.out.println("建议以旧换新");
        else
            System.out.println("不建议以旧换新");
    }

    /*
    让用户依次录入三个整数，求出三个数中的最小值，并打印到控制台
     */
    public static void question2() {
        System.out.println("请依次输入三个整数");
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("三个数中的最小值为 " + Integer.min(num1, Integer.min(num2, num3)));
        scanner.close();
    }

    /*
    某银行推出了整存整取定期储蓄业务，其存期分为一年、两年、三年、五年，到期凭存单支取本息。存款年利率表如下：
        存期		年利率（%）
        一年		2.25
        两年		2.7
        三年		3.25
        五年		3.6
        请存入一定金额（1000起存），存一定年限（四选一），计算到期后得到的本息总额。
        提示：
        存入金额和存入年限均由键盘录入
        本息计算方式：本金+本金×年利率×年限
     */
    public static void question3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入存款金额，至少大于1000");
        String money = scanner.nextLine();//需存金额
        System.out.println("请输入存入年限");
        int years = scanner.nextInt();//存入年限
        double sum = calSum(money, years);   //到期后取出总额
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("到期后能得到的本息总额为 " + sum + "元");
        scanner.close();
    }

    /*
    某商场购物可以打折，具体规则如下：
        普通顾客购不满100元不打折，满100元打9折；
        会员购物不满200元打8折，满200元打7.5折；
        不同打折规则不累加计算。
    请根据此优惠计划进行购物结算，键盘录入顾客的类别（0表示普通顾客，1表示会员）和购物的折前金额（整数即可），输出应付金额（小数类型）。
     */
    public static void question4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入顾客类别");
        int memberType = scanner.nextInt();//顾客类别
        System.out.println("请输入总消费金额");
        int amount = scanner.nextInt();//消费总金额
        double sum;//实付金额
        sum = calConsumeAmount(memberType, amount);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (sum == -1)
            System.out.println("出问题了");
        else
            System.out.println("折后应付 -> " + sum);

        scanner.close();
    }


    /**
     * 计算规则-》普通顾客购不满100元不打折，满100元打9折；会员购物不满200元打8折，满200元打7.5折；
     *
     * @param memberType 顾客类型 0-非会员，1-会员
     * @param amount     应付金额
     * @return
     */
    private static double calConsumeAmount(int memberType, int amount) {
        if (memberType == 1) {
            if (amount < 200)
                return amount * 0.8;
            else
                return amount * 0.75;
        } else if (memberType == 0) {
            if (amount >= 100)
                return amount * 0.9;
            else
                return amount;
        } else {
            return -1;
        }
    }

    /*
     存期		年利率（%）
        一年		2.25
        两年		2.7
        三年		3.25
        五年		3.6
        本息计算方式：本金+本金×年利率×年限 公式
     */
    private static double calSum(String money, int years) {
        double sum = 0;
        BigDecimal principal = new BigDecimal(money);   //本金

        BigDecimal rate1 = new BigDecimal("0.0225");  //一年期的年利率
        BigDecimal rate2 = new BigDecimal("0.027");   //两年期的年利率
        BigDecimal rate3 = new BigDecimal("0.0325");  //三年期的年利率
        BigDecimal rate5 = new BigDecimal("0.036");   //五年期的年利率

        switch (years) {
            case 1:
                sum = calISum(principal, rate1, 1);
                break;
            case 2:
                sum = calISum(principal, rate2, 2);
                break;
            case 3:
                sum = calISum(principal, rate3, 3);
                break;
            case 5:
                sum = calISum(principal, rate5, 5);
                break;
            default:
                System.out.println("暂无此项业务");
                break;
        }

        return sum;
    }

    /**
     * 计算本息公式 本金+本金×年利率×年限 假定利息最低位是角，且采用四舍五入。
     *
     * @param principal 本金
     * @param rate      年利率
     * @return 返回本息
     */
    private static double calISum(BigDecimal principal, BigDecimal rate, int years) {
        return principal.add(principal.multiply(rate).multiply(BigDecimal.valueOf(years)))
                .setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}
