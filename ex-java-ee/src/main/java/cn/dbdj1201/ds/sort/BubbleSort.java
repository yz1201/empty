package cn.dbdj1201.ds.sort;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-04-24 14:51
 * 冒泡排序
 **/
public class BubbleSort {
    public static void main(String[] args) {
        /*
        array[5,3,1,7,2,4]
         */
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = {5, 3, 1, 7, 2, 4};
        System.out.println(Arrays.toString(bubbleSort.sort(arr)));
//        Arrays.sort(arr);

    }

    /**
     * 冒泡排序
     * 每一次浮出最大值，直到有序
     * 需len-1次，第i次需要排len-1-i个数字，因为后边的已经有序了。
     * @param arr
     * @return
     */
    private int[] sort(int[] arr) {
        int len = arr.length;
        int temp;
        boolean flag = false;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag)
                break;
            else
                flag = false;
        }
        return arr;
    }
}
