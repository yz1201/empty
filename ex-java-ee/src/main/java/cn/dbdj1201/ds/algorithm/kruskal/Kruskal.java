package cn.dbdj1201.ds.algorithm.kruskal;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-15 10:50
 **/
public class Kruskal {

    private int edgeNum;    //边的个数
    private char[] vertex;  //顶点数组
    private int[][] matrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};

        //创建KruskalCase 对象实例
        Kruskal kruskal = new Kruskal(vertexs, matrix);
        //输出构建的
//        kruskal.print();
//        kruskal.kruskal();
        EData[] edges = kruskal.getEdges();
        kruskal.sortEdge(edges);
        System.out.println(Arrays.toString(edges));
    }

    public Kruskal(char[] vertex, int[][] matrix) {
//        this.vertex = vertex;
//        this.matrix = matrix;
        int vlen = vertex.length;
        this.vertex = new char[vlen];
        System.arraycopy(vertex, 0, this.vertex, 0, vlen);
        this.matrix = new int[vlen][vlen];
        System.arraycopy(matrix, 0, this.matrix, 0, vlen);

//        for (int[] ints : matrix) {
//            for (int anInt : ints) {
//                if (anInt != INF)
//                    edgeNum++;
//            }
//        }

        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF)
                    edgeNum++;
            }
        }
    }

    public void print() {
        for (int[] ints : this.matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //边排序
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @param ch 顶点值
     * @return 返回顶点下标
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch)
                return i;
        }
        return -1;
    }

    /**
     * 获取图中的边，放到EData数组中
     * 通过matrix邻接矩阵来获取
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点
     *
     * @param ends 记录了各个顶点对应的终点是哪个。ends这个数组是在遍历过程中，逐步形成
     * @param i    顶点下标
     * @return 终点下标
     * ？
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //克鲁斯卡尔算法
    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];
        //创建结果数组，保存最后的最小生成树
        EData[] results = new EData[edgeNum];

        //获取图中所有边的集合，一共有十二条边
        EData[] edges = getEdges();

        sortEdge(edges);

        for (EData edge : edges) {

        }

    }
}

//边的对象实例，包含边的权值，以及两个顶点
class EData {
    char start; //边的一个顶点
    char end;   //另外一个顶点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
