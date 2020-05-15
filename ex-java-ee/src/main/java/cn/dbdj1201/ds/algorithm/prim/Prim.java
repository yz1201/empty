package cn.dbdj1201.ds.algorithm.prim;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-15 8:48
 **/
public class Prim {
    /*
    应用场景：修路问题。
    修路问题本质就是最小生成树问题，先介绍一下最小生成树，Minimum Cost Spanning Tree简称MST
    1，给定一个带权的无向连通图，如何选取一棵最小生成树，使树上所有边上权的总和为最小，这叫最小生成树
    2，N个顶点，一定有N-1条边
    3，包含全部顶点
    4，N-1条边都在图中
    5，求最小生成树主要是普利姆算法跟克鲁斯卡尔算法
     */
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = new int[][]{ //10000表示两个点不连通，其他非零数表示边的权
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        MGraph mGraph = new MGraph(verxs);

        MinTree minTree = new MinTree();
        minTree.GraphBuilder(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);
        minTree.prim(mGraph, 0);
    }
}

class MinTree {

    /**
     * @param graph  图对象
     * @param verxs  顶点个数
     * @param data   顶点数据
     * @param weight 表示边的邻接矩阵
     */
    public void GraphBuilder(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 普利姆算法
     *
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成'A'->0 'B'->1
     */
    public void prim(MGraph graph, int v) {
        int sum = 0;
        //标记顶点的访问情况，0未访问
        int[] visited = new int[graph.verxs];
        //把当前顶点标记为已访问
        visited[v] = 1;
        //记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {//N-1条边N-1次遍历
            //这个确定每一次生成的子图，和哪个顶点的距离最近
            for (int i = 0; i < graph.verxs; i++) {//i表示被访问过的顶点
                for (int j = 0; j < graph.verxs; j++) {//j表示未访问过的顶点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //寻找已经访问过的顶点和未访问顶点间的权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
//            if (minWeight == 10000)
//                break;
            sum += minWeight;
            //找到此次最小边
            System.out.println("edge< " + graph.data[h1] + ", " + graph.data[h2] + " > weight: " + minWeight);
            visited[h2] = 1;//h2已经访问过
            minWeight = 10000;//重置为10000
        }
        System.out.println("sum -> " + sum);
    }
}

class MGraph {
    int verxs;       //顶点个数
    char[] data;    //存放节点数据
    int[][] weight; //存放边，邻接矩阵形式

    public MGraph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}