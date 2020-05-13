package cn.dbdj1201.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author tyz1201
 * @datetime 2020-05-13 10:24
 **/
public class ArrayGraph {
    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 8;
//        String[] vertexValue = {"a", "b", "c", "d", "e"};
        String[] vertexValue = {"1", "2", "3", "4", "5","6","7","8"};

        ArrayGraph graph = new ArrayGraph(n);

        for (String s : vertexValue) {
            graph.insertVertex(s);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);


        graph.showGraph();
        System.out.println();
        graph.bfs();
        System.out.println();
        graph.dfs();
    }

    public ArrayGraph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        this.isVisited = new boolean[n];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //得到第一个邻接节点的下标

    /**
     * @param index
     * @return 返回第index行的邻接节点
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0)
                return j;
        }

        return -1;
    }

    /**
     *
     * @param v1
     * @param v2
     * @return v2后边的第v1行的邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0)
                return j;
        }
        return -1;
    }

    /**
     * @param isVisited 是否被访问过
     * @param i         第一次是0，初始顶点
     */
    private void dfs(boolean[] isVisited, int i) {
        //访问第一个顶点
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;  //将访问标志置为true
        //查找顶点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载
    //遍历所有结点，并进行dfs
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                dfs(isVisited, i);
        }

    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头节点对应的下标
        int w;//邻接节点w
        LinkedList<Integer> queue = new LinkedList<>();//记录访问顺序
        System.out.print(getValueByIndex(i) + " -> ");//访问节点
        isVisited[i] = true;//标记已访问
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u); //找到一个未遍历到且未被访问过的邻接节点
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }

                //以第u行，找w的下一个邻接节点。
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                bfs(isVisited, i);
        }
    }

    /**
     * 无向图
     *
     * @param v1     表示点的下标即是第几个顶点，A-B A-》0 B-》1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点*对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的邻接矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));

//            for (int i : edge) {
//                System.out.println(i);
//            }
        }
    }


}
