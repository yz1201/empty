package cn.dbdj1201.ds.sort;

import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * @author tyz1201
 * @datetime 2020-04-25 13:42
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 4, 7, 3, 2, 11};
        shell2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序，随着增量逐渐减少，每组包含的关键词越多，当增量减至1时，整个文件恰好被分成一组
    算法结束。
     */
    public static void shell(int[] arr) {
        int temp;
        /*


         */
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }


    public static void shell2(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j >= gap && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }

                    arr[j] = temp;
                }

            }
        }
    }
}
