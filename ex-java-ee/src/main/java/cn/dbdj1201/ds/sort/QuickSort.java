package cn.dbdj1201.ds.sort;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-02 10:35
 * 递归-快速排序
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {5, 7, 6, 2, 1, 4};
        quick(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr, int left, int right) {
        int l = left;//左指针
        int r = right;//右指针
        int pivot = arr[(left + right) / 2]; //中位数pivot
        int temp;
        while (l < r) {
            //定位到左半部分大于等于pivot的数
            while (arr[l] < pivot) {
                l++;
            }
            //定位到右半部分小于等于pivot的数
            while (arr[r] > pivot) {
                r--;
            }
            //如果左右指针重叠或者交叉,退出循环
            if (l >= r) {
                break;
            }

            //交换左右两边不合适的数字
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //交换过后，若左=pivot，需将右指针前移，指向前一个。
            if (arr[l] == pivot) {
                r--;
            }

            //
            if (arr[r] == pivot)
                l++;

            //如果左指针右指针重合，各自移动一位。
            if (l == r) {
                l++;
                r--;
            }

            //左半部分递归
//            if (left < r)
//                quick(arr, left, r);
            //右半部分递归
//            if (right > l)
//                quick(arr, l, right);

        }
    }
}
