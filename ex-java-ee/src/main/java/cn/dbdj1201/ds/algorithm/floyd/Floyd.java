package cn.dbdj1201.ds.algorithm.floyd;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tyz1201
 * @datetime 2020-05-16 14:57
 **/
public class Floyd {
    /*
        应用场景-最短路径问题，每个顶点到其他顶点的最短距离。
     */
    public static void main(String[] args) {
        //图初始化
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.showGraph();
        graph.floyd();
        System.out.println("======================");
        graph.showGraph();
    }
}

class Graph {
    private char[] vertex;      //顶点数组
    private int[][] disMatrix;  //记录从各个顶点出发到其他顶点的距离。
    private int[][] preMatrix;  //记录到达目标顶点的前驱顶点/中间顶点。

    /**
     * @param length 顶点个数
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.disMatrix = matrix;
        this.preMatrix = new int[length][length];
        //前驱顶点矩阵初始化
        for (int i = 0; i < length; i++) {
            Arrays.fill(preMatrix[i], i);
        }
    }

    public void showGraph() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.println("距离矩阵");
        for (int[] ints : this.disMatrix) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println("=======================");
        System.out.println("前驱顶点矩阵");
        for (int[] ints : this.preMatrix) {
//            System.out.println(Arrays.toString(ints));
            Stream.of(ints).forEach(arr -> {
                Arrays.stream(arr).forEach(num -> System.out.print(vertex[num] + " "));
                System.out.println();
            });
        }
    }

    //弗洛伊德算法
    public void floyd() {
        int len;
        //对中间顶点的遍历,k是中间顶点下标 i->k->j
        for (int k = 0; k < disMatrix.length; k++) {
            //出发顶点
            for (int i = 0; i < disMatrix.length; i++) {
                //终点
                for (int j = 0; j < disMatrix.length; j++) {
                    len = disMatrix[i][k] + disMatrix[k][j];//从i到j距离
                    //判断经过次中间点的距离与原本直连距离大小
                    if (len < disMatrix[i][j]) {
                        disMatrix[i][j] = len;
                        preMatrix[i][j] = preMatrix[k][j];//最短距离更新也要更新k为最小路径的前驱顶点
                    }
                }

            }
        }
    }
}
