package cn.dbdj1201.ds.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @author tyz1201
 * @datetime 2020-05-05 11:28
 **/
public class RadixSort {
    /*
        基数排序属于分配式排序，又称桶子法，顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些桶中，打到排序目的。
        基数排序是属于稳定性的排序，而且效率还不错
        基数排序是桶排序的扩展
        基数排序是XXX发明的，实现如下：
            将整数按位数切割称不同数字，然后按每个位数分别比较。
        经典空间换时间，可能会出现内存不足问题，速度很快。
     */

    public static void main(String[] args) {
        int[] arr = {5, 4, 17, 256, 1, 85, 888};
        radix(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Integer.MAX_VALUE);
    }

    public static void radix(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int maxLength = (max + "").length();

        for (int o = 0, n = 1; o < maxLength; o++, n *= 10) {
            int[][] bucket = new int[10][arr.length];
            int[] bucketElementCounts = new int[10];
            for (int i : arr) {
                int digitOfElement = i / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = i;
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            for (int k = 0; k < 10; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }

//            System.out.println("第" + (o + 1) + " --->" + Arrays.toString(arr));
        }


    }
}
