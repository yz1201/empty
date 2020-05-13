package cn.dbdj1201.ds.algorithm.seach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-05 15:10
 **/
public class Search {

    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 5, 5, 7, 7, 9};
        System.out.println(insertVal(arr, 0, 7, 7));
    }

    /*
    二分法从有序数组中查找特定数字
     */

    /**
     * @param arr    已排序的数组
     * @param right  右指针起始位置
     * @param left   左指针起始
     * @param target 目标数
     * @return 没找到返回空，找到了返回数组中所有与目标数相等的值下标
     */
    public static List<Integer> binary(int[] arr, int right, int left, int target) {
        //递归结束条件：左右交叉，目标数比最小值小 or 比最大值大
        if (left > right || target < arr[left] || target > arr[arr.length - 1])
            return null;

        int mid = (right + left) / 2;
        int midVal = arr[mid];

        if (target > midVal) {
            return binary(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return binary(arr, left, mid - 1, target);
        } else {
            //找到了第一个目标数之后，在其附近寻找是否还有
            List<Integer> indexList = new ArrayList<>();
            int tempIndex = mid - 1;
            while (tempIndex >= 0 && arr[tempIndex] == target)
                indexList.add(tempIndex--);
            indexList.add(mid);
            tempIndex = mid + 1;
            while (tempIndex <= arr.length - 1 && arr[tempIndex] == target)
                indexList.add(tempIndex++);

            return indexList;
        }
    }

    public static List<Integer> insertVal(int[] arr, int left, int right, int target) {
        if (left > right || target < arr[left] || target > arr[arr.length - 1])
            return null;

        int mid = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (target > midVal) {
            return insertVal(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return insertVal(arr, left, mid - 1, target);
        } else {
            List<Integer> indexList = new ArrayList<>();
            int tempIndex = mid - 1;
            while (tempIndex >= 0 && arr[tempIndex] == target)
                indexList.add(tempIndex--);
            indexList.add(mid);
            tempIndex = mid + 1;
            while (tempIndex <= arr.length - 1 && arr[tempIndex] == target)
                indexList.add(tempIndex++);
            return indexList;
//            return mid;
        }
    }

    public static int fibSearch(int[] arr, int target) {
        int right = arr.length - 1;
        int left = 0;
        int k = 0;
        int mid = 0;
        int[] fib = fib();

        while (right > fib[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(arr, fib[k]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            mid = left + fib[k - 1] - 1;
            if (target < temp[mid]) {
                right = mid - 1;
                k--;
            } else if (target > temp[mid]) {
                left = mid + 1;
                k = k - 2;
            } else {
                return Math.min(mid, right);
            }
        }

        return -1;
    }

    public static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

}
