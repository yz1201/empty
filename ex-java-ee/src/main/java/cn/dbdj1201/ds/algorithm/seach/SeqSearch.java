package cn.dbdj1201.ds.algorithm.seach;

/**
 * @author tyz1201
 * @datetime 2020-05-05 13:38
 * 线性查找，按顺序查询target
 **/
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {25, 12, 3, 4, 2022, 45687};
        System.out.println(seqSearch(arr,3));
    }

    public static int seqSearch(int[] arr, int targetValue) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetValue)
                return i;
        }
        return -1;
    }
}
