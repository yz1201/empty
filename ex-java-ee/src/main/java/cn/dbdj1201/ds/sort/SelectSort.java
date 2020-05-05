package cn.dbdj1201.ds.sort;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-04-25 12:05
 **/
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {5, 7, 3, 1, 4, 6};
        select1(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void select(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /*
        每次排序都选出最小的数，并与i下标交换
     */
    public static void select1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
