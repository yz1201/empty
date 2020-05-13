package cn.dbdj1201.ds.algorithm;

/**
 * @author tyz1201
 * @datetime 2020-05-13 16:59
 **/
public class ForkJoinAlgorithm {
    /*
    分治算法：（Divide-and-Conquer）
        把一个复杂问题分解成两个或者更多的相同或相似的问题，再把子问题分成更小的子问题，直到最后子问题可以简单的直接求解，原问题的解即子问题的解的合并，
     这个技巧是很多高效算法的基础，如排序算法(快速排序，归并排序)，傅里叶变换（快速傅里叶变换）

     分治算法可以求解的一些经典问题：
        二分搜索
        大整数乘法
        棋盘覆盖
        合并排序
        快速排序
        线性时间选择
        最接近点对问题
        循环赛日程问题
        汉诺塔

      分治算法步骤：
        1，分解：
            将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
        2，解决：
            若子问题规模较小而且容易解决则直接解决，否则继续递归分解
        3，合并：
            将各个子问题的解合并为原问题的解。
        这里以汉诺塔为例
        思路：
            如果只有一个盘，A -> C
            如果n>=2,我们可以当作两个盘，上边的所有盘，跟最下边的盘，我们只需：
                ①，先把最上面的盘A->B
                ②，把最下边的盘A->C
                ③，把上面的盘(即B塔上的所有盘)挪到C。
     */
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
        System.out.println(times);
    }

    //汉诺塔移动方法
    //使用分治算法

    private static int times; //记录移动次数，其实就是2^n-1;

    /**
     * @param num 要挪的盘子个数
     * @param a   A塔 起始塔
     * @param b   B塔 中间塔
     * @param c   C塔 终点塔
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        times++;
        if (num < 1)
            System.out.println("？");
        if (num == 1)
            System.out.println("第1个盘从 " + a + " -> " + c);
        else {
            //先把上面盘从A到B，中间用到C
            hanoiTower(num - 1, a, c, b);
            System.out.println("第" + num + "个盘从 " + a + " -> " + c);
            //把B塔上剩的盘子挪到C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
