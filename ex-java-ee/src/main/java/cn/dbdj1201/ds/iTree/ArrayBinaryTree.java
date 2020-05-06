package cn.dbdj1201.ds.iTree;

import org.hibernate.validator.constraints.Length;

/**
 * @author tyz1201
 * @datetime 2020-05-06 21:37
 **/
public class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     * @param index 数组下标
     */
    public void preOrder(int index) {
        this.judgeArr();
        System.out.println(arr[index]);

        if (index * 2 + 1 < arr.length)
            preOrder(2 * index + 1);

        if (index * 2 + 2 < arr.length)
            preOrder(2 * index + 2);
    }

    //方法重载，方便调用。
    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    //中序遍历
    public void infixOrder(int index) {
        this.judgeArr();

        if (index * 2 + 1 < arr.length)
            infixOrder(index * 2 + 1);
        System.out.println(arr[index]);
        if (index * 2 + 2 < arr.length)
            infixOrder(index * 2 + 2);
    }

    //后序遍历
    public void postOrder(int index) {
        this.judgeArr();

        if (index * 2 + 1 < arr.length)
            postOrder(index * 2 + 1);

        if (index * 2 + 2 < arr.length)
            postOrder(index * 2 + 2);

        System.out.println(arr[index]);
    }


    public void judgeArr() {
        //如果数组为空
        if (arr.length == 0 || arr == null) {
            System.out.println("出问题了");
        }
    }
}
