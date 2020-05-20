package cn.dbdj1201.demo.section2;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-21 5:04
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {5, 6, 2, 4, 1, 3};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
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
