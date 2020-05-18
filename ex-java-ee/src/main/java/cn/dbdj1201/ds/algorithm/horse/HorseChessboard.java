package cn.dbdj1201.ds.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-18 9:01
 **/
public class HorseChessboard {
    /*
        马踏棋盘/骑士周游问题
     */
    private static int X;//行数
    private static int Y;//列数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean[] visited;
    private static boolean finished;//true->棋盘被踏完了。

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        traversalChessboard(chessboard, row - 1, column - 1, 1);

        for (int[] ints : chessboard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param chessboard 棋盘数组
     * @param row        行，0开始
     * @param column     列
     * @param step       走的第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true; //二维数组中的第row，column个数，记录成一维数组的第row*X+column下标个数。
        ArrayList<Point> next = next(new Point(column, row));//获取当前位置可以走的下一个位置的集合
        //贪心算法优化，对当前位置集合按照其下一步的位置集合数目多寡进行排序，递增👉。每一次都选之后路最少的
        next.sort(Comparator.comparingInt(curPoint -> next(curPoint).size()));
        while (!next.isEmpty()) {
            Point point = next.remove(0);//取出一个可以走的位置
            if (!visited[point.y * X + point.x]) {//该位置未被访问过
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }

        /*
        判断是否覆盖
            走的步数是否等于棋盘点位个数
            是否满足棋盘被覆盖的条件
         */
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    /**
     * 根据当前的点，算出可走的下一个点
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //表示小马可以走位置5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断小马可以走6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断小马可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断小马可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断小马可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断小马可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断小马可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断小马可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }

    public static void sort(ArrayList<Point> points) {
        points.sort(Comparator.comparingInt(p -> next(p).size()));
    }
}