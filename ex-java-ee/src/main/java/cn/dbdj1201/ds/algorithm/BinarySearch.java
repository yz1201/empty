package cn.dbdj1201.ds.algorithm;

/**
 * @author tyz1201
 * @datetime 2020-05-13 16:41
 * 二分查找，非递归方式
 **/
public class BinarySearch {
    /*
    二分查找的时间负责度是log2N
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(binarySearch(arr, 332));
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

}
