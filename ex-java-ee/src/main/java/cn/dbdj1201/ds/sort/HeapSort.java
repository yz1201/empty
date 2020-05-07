package cn.dbdj1201.ds.sort;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-07 11:15
 **/
public class HeapSort {
    /*
    堆排序是利用堆这种数据结构设计的算法，是一种选择排序，它最好最坏平均的时间复杂度都是nlogn，线性对数阶。它是不稳定排序
    堆是具有以下性质的完全二叉树：
        每个节点的值都>=其左右孩子节点的值，这种称为大顶堆，注意：没要求其左右孩子节点间的值的大小关系
        每个节点的值都<=其左右孩子节点的值，这种叫小顶堆。
      升序大顶堆，降序小顶堆

      堆排序思路：
        将待排序序列构造为大顶堆
        此时，整个序列最大值就是堆顶的根节点
        将其与末尾元素进行交换，此时末尾就为最大值
        然后将剩余n-1个元素重新构造成了一个堆，这样会得到n个元素的次小值，如此反复执行，就得到一个有序的序列。
     */

    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        heap(arr);
    }

    public static void heap(int[] arr) {

        //把数组调整为大顶堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--)
            adjustHeap(arr, i, arr.length);

        System.out.println(Arrays.toString(arr));
        for (int j = arr.length - 1; j > 0; j--) {
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;

            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (arr[k] < arr[k + 1] && k + 1 < length) {//左子节点的值小于右子节点的值
                k++; //k记录较大值的下标
            }

            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k]; //把较大值赋给当前节点
                i = k;//i指向k，继续循环比较
            } else
                break;//
        }

        //循环结束后，当前最大值已经放置在i位置。需把交换的值放在原来的k位置
        arr[i] = temp;
    }
}
