package cn.dbdj1201.ds.recursive;

/**
 * @author tyz1201
 * @datetime 2020-04-23 15:29
 * 迷宫问题，二维数组模拟迷宫，递归走出迷宫
 **/
public class RecursiveDemo {
    /*
        重要规则：
            java虚拟机执行一个方法时，就新建一个受保护的独立空间，栈空间
            方法的局部变量是独立的，不会互相影响
            如果是引用类型的遍历，比如数组，就会共享该引用类型的数据
            *每次递归必须逼近他的退出递归的条件，不然堆栈溢出
            当一个方法执行完毕，或者遇到return，就会返回调用这次递归的方法。
         可以解决数学问题：
            八皇后，汉诺塔，迷宫，球和篮子
         算法中用得上：快速排序，归并排序，二分查找，分治算法
         用栈解决的问题，转化为递归，代码量会减少很多。
     */

    public static void main(String[] args) {
        //模拟出迷宫地图
        int[][] map = new int[8][7];
        //使用1表示墙体，2表示正确路径，0表示空置,还没走过，3表示走过，但走不通。
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
        printArrays(map);
        setWay(map, 1, 1);
        System.out.println("---->");
        printArrays(map);
    }

    /**
     * 二维数组的打印
     *
     * @param arrays
     */
    public static void printArrays(int[][] arrays) {
        int columnLen = arrays[0].length;
//        System.out.println(rowLen + " -> " + columnLen);
        for (int[] array : arrays) {
            for (int j = 0; j < columnLen; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路

    /**
     * (i,j)出发,终点(6,5)
     * 当map[i][j]值为0时表示没有走过
     * 使用1表示墙体，2表示正确路径，0表示空置,还没走过，3表示走过，但走不通。
     * 行走顺序：下->右->上->左，如果走不通，回溯。
     *
     * @param map 地图
     * @param i   从哪个位置开始
     * @param j   从哪个位置开始
     * @return 找到/没找到
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2)
            return true; //递归结束条件达成
        else {
            if (map[i][j] == 0) {//当前节点还没走过
                //顺序 ↓ → ↑ ←
                map[i][j] = 2;//假定可以走通
                if (setWay(map, i + 1, j)) {//↓
                    return true;
                } else if (setWay(map, i, j + 1)) {//→
                    return true;
                } else if (setWay(map, i - 1, j)) {//↑
                    return true;
                } else if (setWay(map, i, j - 1)) {//←
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j] 不等于0，即其值为1/2/3，都是不用再次判断的状态，标记为false。
                return false;
            }
        }
    }

}
