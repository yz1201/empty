package cn.dbdj1201.concurrent.clazz4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author tyz1201
 * @datetime 2020-04-09 17:23
 **/
public class ParallelStreamApi {

    private static int countNum1 = 0;
    private static int countNum2 = 0;

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 10000; i++)
            nums.add(1000000 + new Random().nextInt(1000000));
        long start1 = System.currentTimeMillis();
        nums.forEach(num -> {
            if (isPrime(num))
                countNum1++;

        });
        long end1 = System.currentTimeMillis();
        System.out.println("method 1 consumes -> " + countNum1 + " -> " + (end1 - start1));

        long start2 = System.currentTimeMillis();
        nums.parallelStream().forEach(num -> {
            if (isPrime(num))
                countNum2++;
        });
        long end2 = System.currentTimeMillis();
        System.out.println("method 2 consumes -> " + countNum2 + " -> " + (end2 - start2));
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
