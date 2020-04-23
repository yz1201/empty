package cn.dbdj1201.ds.recursive;

/**
 * @author tyz1201
 * @datetime 2020-04-23 16:35
 * 八皇后问题
 * 在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法
 **/
public class EightQueen {
    /*
    回溯算法：
        第一次从（1，1）开始，往下判断，怎么判断？（2，1）可以？（2，2）？找到合适的节点，再向下。
        第二次从（1，2）开始
        同理。
        答案用一维数组即可表示：[0,4,7,5,2,6,1,3]，因为其下标表示它处于第几（-1）列。
        行数保存在下标中，列数保存在数组值中。
     */

    private final int max = 8;
    private final int[] array = new int[max];
    private static int times;

    public static void main(String[] args) {
        EightQueen eq = new EightQueen();
        eq.check(0);
        System.out.println("times: " + times);
    }

    //放置皇后
    private void check(int n) {
        if (n == max) {//n=8,表示前八个放置完毕
            print();
            return;
        }
        //依次放置皇后，并进行冲突检测
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后放置到正确位置
            array[n] = i;
            //判断冲突
            if (judge(n)) {//不冲突
                //放置下一个
                check(n + 1);
            }
        }
    }


    /**
     * 放置第n个皇后时判断是否跟之前的冲突
     *
     * @param n
     * @return true不冲突，false冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]是否同列
            //后边的条件在判断二者是否在同一斜线。
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置打印出来
    private void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        times++;
    }
}
