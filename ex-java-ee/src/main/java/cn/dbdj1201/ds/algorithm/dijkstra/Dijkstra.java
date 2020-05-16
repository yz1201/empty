package cn.dbdj1201.ds.algorithm.dijkstra;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author tyz1201
 * @datetime 2020-05-16 9:26
 **/
public class Dijkstra {
    /*
    应用场景-最短路径问题
    迪特斯特拉算法是典型的最短路径算法，用于计算一个节点到其他节点的最短路径，它的主要特点是以起始点为中心向外层层扩展(广度优先搜索BFS)，直到扩展到终点。
     */

    public static void main(String[] args) {
        //初始化图
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

        graph.dijkstra(6);
        graph.showResult();
    }
}

//图
class Graph {
    private char[] vertex;  //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv;//已访问顶点

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //打印图
    public void showGraph() {
        for (int[] ints : this.matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param index 表示出发顶点对应下标
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(this.vertex.length, index);
        update(index);

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);
        }
    }

    //展示dijstra算法最后结果
    public void showResult() {
        System.out.println("===================");
        this.vv.show();
    }

    //更新index下标顶点到周围顶点的距离，更新index下标顶点到周围顶点的前驱顶点
    private void update(int index) {
        int len;
        //遍历邻接矩阵的第index行
        for (int j = 0; j < matrix[index].length; j++) {
            //len是出发顶点到index顶点的距离+从index顶点到j顶点的距离之和。
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);  //更新目前路径中给j顶点的前驱顶点为index顶点
                vv.updateDis(j, len);    //更新目前路径中到j顶点的距离
            }
        }
    }


}

//已访问顶点集合
class VisitedVertex {
    public int[] already_arr; //记录各个顶点是否已访问过，1-已访问，0-未访问
    public int[] pre_visited; //每个下标对应的值为其前一个顶点的下标
    public int[] dis;         //记录出发顶点堆其他所有顶点的距离，eg：G出发，就记录从G到其他顶点的距离。

    /**
     * @param length 顶点个数
     * @param index  出发顶点对应下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(this.dis, 65535);
        this.already_arr[index] = 1;//出发顶点默认被访问过。
        this.dis[index] = 0;
    }

    /**
     * 判断index下标的顶点是否被访问过
     *
     * @param index
     * @return 访问过-true
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param length
     */
    public void updateDis(int index, int length) {
        dis[index] = length;
    }

    /**
     * 更新pre顶点的前驱顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        this.pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的顶点，此例中G为出发顶点，G之后将A作为新的访问顶点
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show() {
        System.out.println(Arrays.toString(this.already_arr));
        System.out.println(Arrays.toString(this.pre_visited));
        System.out.println(Arrays.toString(this.dis));
    }
}

