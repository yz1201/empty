package cn.dbdj1201.ds.iTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-07 20:28
 **/
public class HuffmanTree {
    /*
    给定n个权值作为n个叶子节点，构造一棵二叉树，若该树的带权路径长度达到最小，称这样的二叉树为最优二叉树，也就是霍夫曼树
    霍夫曼树是带权路径长度最短的树，权值较大的节点离根较近。

    路径：
        在一棵树中，从一个节点往下可以达到的孩子或者孙子节点之间的通路称为路径
    路径长度：
        通路中分支的数目称为路径长度。若规定根节点的层数为1，则从根节点到第l层节点的路径长度为l-1
    节点的权及带权路径长度：
        若将树中节点赋给一个有着带有某种意义的数值，则称这个数值为该节点的权。
        节点的带权路径长度为：从根节点到该节点之间的路径长度与该节点的权的乘积。
    树的带权路径长度：树的带权路径长度规定为所有叶子节点的带权路径长度之和，记为wpl(weighted path length)，权值越大的节点离根节点越近的二叉树才是最优二叉树。
    wpl最小的就是赫夫曼树

    赫夫曼编码也翻译为哈夫曼编码huffman coding 又称霍夫曼编码，是一种编码方式，属于一种程序算法
    赫夫曼编码是赫夫曼树在电讯通信中的经典应用之一
    赫夫曼编码广泛地应用于数据文件压缩，其压缩率通常在20%-90%之间
    赫夫曼码是可变字长编码(VLC)的一种，huffman于1952年提出的一种编码方法，称之为最佳编码。
     */


    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
//        huffmanTreeBuild(arr);
        preOrderList(huffmanTreeBuild(arr));
    }


    public static Node1 huffmanTreeBuild(int[] arr) {
        //遍历arr
        //将arr的每个元素都变成节点
        //将node放入arrayList 中
        List<Node1> node1s = new ArrayList<>();
        for (int i : arr) {
            node1s.add(new Node1(i));
        }

        while (node1s.size() > 1) {
            Collections.sort(node1s);//利用工具类排序
//            System.out.println(node1s);

            //取出权值最小的两棵二叉树
            //取出权值最小的节点
            Node1 leftNode = node1s.get(0);
            //取出权值第二小的节点
            Node1 rightNode = node1s.get(1);
            //构建新的二叉树
            Node1 parent = new Node1(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从list中删除处理过的二叉树
            node1s.remove(leftNode);
            node1s.remove(rightNode);
            //加入新增的parent node 到list中
            node1s.add(parent);
        }

        return node1s.get(0);
    }

    public static void preOrderList(Node1 root) {
        if (root != null) {
            root.preOrderList();
        } else
            System.out.println("trouble");
    }
}

class Node1 implements Comparable<Node1> {
    int value;
    Node1 left;
    Node1 right;


    public Node1(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrderList() {
        System.out.println(this);

        if (this.left != null)
            this.left.preOrderList();

        if (this.right != null)
            this.right.preOrderList();

    }

    @Override
    public int compareTo(Node1 o) {
        return this.value - o.value;
    }

}
