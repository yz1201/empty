package cn.dbdj1201.ds.algorithm;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-13 21:11
 **/
public class DynamicProgramAlgorithm {
    /*
    应用场景，背包问题
    有一个背包，容量4磅，现有如下物品
    物品      重量      价格
    吉他      1        1500
    音响      4        3000
    电脑      3        2000
    1，要求达到的目标为装入背包的物品总价值最大，并且不超重
    2，要求装入的物品不重复

    动态规划 dynamic programming 核心思想：
        将带问题划分为小问题进行解决，从而一步步获取最优解的处理方法

    动态规划算法跟分治算法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这些子问题的解得到原问题的解

    不同点：
        适合于用动态规划求解的问题，经分解得到的子问题往往不是相互独立的(即下一个子阶段的求解往往是建立在上一个子阶段的解的基础上，再进行下一步求解)

    动态规划可以通过填表的方式来逐步推进，得到最优解。

    背包问题思路图解：
        分为01背包跟完全背包，完全背包意思是每件物品无限可用
        这里的题目是01背包，每件东西只能放一个，无限背包可以转化为01背包

     */

    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4; //背包容量
        int n = val.length;//物品的个数

        //创建二维数组，表示在前i个物品当中能够装入容量为j的背包中的最大价值。
        int[][] v = new int[n + 1][m + 1];

        //记录放入商品的情况
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        Arrays.fill(v[0], 0);

        for (int i = 1; i < v.length; i++) {

            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) { //这里是从第二行开始的，但w质量数组中其实从0开始的，需-1
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], (val[i - 1] + v[i - 1][j - w[i - 1]]));
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }

        }

        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
        }

        for (int[] ints : path) {
            System.out.println(Arrays.toString(ints));
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第"+i+"件商品放入背包");
                j -= w[i - 1]; //?
            }
            i--;
        }
    }
}
