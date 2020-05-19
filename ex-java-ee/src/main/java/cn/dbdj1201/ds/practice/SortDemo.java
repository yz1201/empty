package cn.dbdj1201.ds.practice;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-14 10:53
 **/
public class SortDemo {
    private static int times = 0;

    public static void main(String[] args) {
        int[] arr = {5, 7, 6, 3, 1, 9};
//        int[] arr = {4, 6, 8, 5, 9};
//        bubbling(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(times);
        quick(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /*
        int len = arr.length-1;
        冒泡，每次只选出一个极值
        需len次排序
        第i需要对相邻两个排序，要排len-i-1次
        时间复杂度平方阶o(n^2) 最好最坏平均都是，而且这个是稳定排序
     */
    public static void bubbling(int[] arr) {
        boolean flag = true;

        for (int i = 0; i < arr.length - 1; i++) {
            //第i次查找只需对arr.length-i-1个数进行排序
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] >= arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            times++;
            //没有交换说明已经有序，直接退出
            if (flag)
                break;
            else
                flag = true;
        }
    }

    /*
    堆排序，线性对数阶，不需额外空间，不稳定排序，较大数据量时采用。
        思路：
            调整为大顶堆，
            交换根节点与最小节点
            继续调整为大顶堆
            继续交换
     */
    public static void heapSort(int[] arr) {
        /*
        从二叉树的最后一个非叶子节点开始调整，直至每个子树都是大顶堆
         */
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustBigHeadHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;

            adjustBigHeadHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整
     */
    private static void adjustBigHeadHeap(int[] arr, int i, int length) {
        int temp = arr[i];//用临时变量保存
        int k = 2 * i + 1;
        for (; k < length; k = 2 * k + 1) {
            //比较左右子节点的大小，谁大，k取谁的值。注意，
            if (k + 1 < length && arr[k] < arr[k + 1])
                k++;
            //将父节点的值跟左/右子节点中的较大值进行比较，将较大值赋给父节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else
                break;
        }
        //循环结束后，进行交互。
        arr[i] = temp;
    }

    /*
    插入排序
        :少量元素排序比较好用，就像整理扑克牌，从第二个元素开始整理前边的
     */
    public static void insert(int[] arr) {
        //外层从第二个元素遍历
        //内层往前遍历，如果满足条件交互相邻位置元素
        for (int i = 1; i < arr.length - 1; i++) {

            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else
                    break;
            }

        }
    }

    /*
    快速排序 时间复杂度 线性对数阶 最坏平方阶，不稳定排序
    */
    public static void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp;
        while (l < r) {

            //定位到非法数
            while (arr[l] < pivot)
                l++;
            while (arr[r] > pivot)
                r--;

            if (l >= r)
                break;
            //交互
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            //防止有相同数导致的死循环
            if (arr[l] == pivot)
                r--;
            if (arr[r] == pivot)
                l++;
        }

        //防止栈溢出
        if (l == r) {
            l++;
            r--;
        }

        //向左递归
        if (left < r)
            quick(arr, left, r);
        //向右递归
        if (right > l)
            quick(arr, l, right);
    }

}
