package cn.dbdj1201.ds.sort;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-02 15:04
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 7, 6, 2, 1, 4, 3};
        int[] temp = new int[arr.length];
        fork(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }


    public static void fork(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;

            fork(arr, left, mid, temp);
            fork(arr, mid + 1, right, temp);

            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引？
     * @param right 右边有序序列的初始索引
     * @param temp  中间数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println(":)");
        int i = left;
        int j = mid + 1;
        int t = 0;

        //从左至右合并到临时数组
        //0-mid mid-right 两个数组，合并到temp
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //一个数组合并完另一个还有剩余时，直接并入temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将temp copy给原始数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}
