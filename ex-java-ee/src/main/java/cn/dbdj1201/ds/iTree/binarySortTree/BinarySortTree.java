package cn.dbdj1201.ds.iTree.binarySortTree;

/**
 * @author tyz1201
 * @datetime 2020-05-11 21:15
 **/
public class BinarySortTree {
    /*
    数组查找快，增删慢
    链表增删快查询慢
    二叉排序树解决你的烦恼
    概念：对于二叉排序树，它的任意一个非叶子节点，要求其左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。如果相等，则左右皆可。
     */

    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : array) {
            binarySortTree.add(new Node3(i));
        }

        binarySortTree.infixOrder();
    }

    private Node3 root;

    public void add(Node3 node3) {
        if (this.root == null)
            root = node3;
        else
            root.add(node3);
    }

    public void infixOrder() {
        if (this.root != null)
            root.infixOrder();
        else
            System.out.println("trouble");
    }
    /*
    二叉排序树的删除，分三种情况：
    1，删除叶子节点
    2，删除只有一颗子树的节点
    3，删除有两棵子树的节点
     */

}

class Node3 {
    int value;
    Node3 left;
    Node3 right;

    public Node3(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node3{" +
                "value=" + value +
                '}';
    }

    /**
     * @param node3
     */
    public void add(Node3 node3) {
        /*
        添加新节点，先跟根节点的value比较
        小于就看左子树情况，无节点就直接挂在左子节点。有就左子树递归添加新节点
        大于等于同左。
         */
        if (node3 == null)
            return;
        if (node3.value < this.value) {
            //如果当前节点的左子节点为空
            if (this.left == null) {
                this.left = node3;
            } else {
                this.left.add(node3);
            }
        } else {
            if (this.right == null)
                this.right = node3;
            else
                this.right.add(node3);
        }
    }

    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();

        System.out.println(this);

        if (this.right != null)
            this.right.infixOrder();
    }
}
