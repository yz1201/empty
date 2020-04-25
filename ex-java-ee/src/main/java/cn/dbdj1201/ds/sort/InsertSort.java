package cn.dbdj1201.ds.sort;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-04-25 12:21
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 1, 2};
        insert(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     * 从第二个元素开始，与之前元素进行比较，插入。
     *
     * @param arr
     */
    public static void insert(int[] arr) {
        int insertIndex;
        int insertVal;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertVal = arr[i];

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

        }
    }
}
