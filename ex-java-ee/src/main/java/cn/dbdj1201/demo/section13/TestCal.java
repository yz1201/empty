package cn.dbdj1201.demo.section13;

/**
 * @author tyz1201
 * @datetime 2020-05-23 10:43
 **/
public class TestCal {

    public static void main(String[] args) {
        int result = cal(Integer::sum, 1, 2);
        System.out.println(result);

    }

    public static int cal(Calculator calculator, int a, int b) {
        return calculator.add(a, b);
    }
}
