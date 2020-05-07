package cn.dbdj1201.ds.iTree;

/**
 * @author tyz1201
 * @datetime 2020-05-06 21:26
 * 顺序存储二叉树
 **/
public class SortStorageBinaryTree {

    /*
    从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组。
    特点：
        通常只考虑完全二叉树
        第n个元素的左子节点为2*n+1
        第n个元素的右子节点为2*n+2
        第n个元素的父节点为（n-1）/2
        n从0开始。


     线索化二叉树：
        n个节点的二叉链表中含有n+1 公式【2n-（n-1）=n+1】个空指针域，利用二叉链表中的空指针域，存放指向该节点在某种遍历次序下的前驱和后继节点的指针（这种附加的
        指针称为线索）
        这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树，根据线索性质的不同，称为前序线索二叉树，中序线索二叉树，后续线索二叉树。
        按照某种顺序遍历时:
            一个节点的前一个节点，称为前驱节点
            一个节点的后一个节点，称为后继节点
     */

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
//        tree.preOrder();
//        System.out.println("=====");
//        tree.infixOrder();
//        System.out.println("=====");
//        tree.postOrder();

        IHeroNode songJiang = new IHeroNode(1, "song jiang");
        IHeroNode wuYong = new IHeroNode(3, "wu yong");
        IHeroNode luJunYi = new IHeroNode(6, "lu jun yi");
        IHeroNode linChong = new IHeroNode(8, "lin chong");
        IHeroNode guanSheng = new IHeroNode(10, "guan sheng");
        IHeroNode mary = new IHeroNode(14, "mary");

        ClueBinaryTree tree = new ClueBinaryTree();
        songJiang.setLeft(wuYong);
        songJiang.setRight(luJunYi);
        wuYong.setLeft(linChong);
        wuYong.setRight(guanSheng);
        luJunYi.setLeft(mary);
        wuYong.setParent(songJiang);
        luJunYi.setParent(songJiang);
        linChong.setParent(wuYong);
        guanSheng.setParent(wuYong);
        mary.setParent(luJunYi);
        tree.setRoot(songJiang);

        tree.clueNodePostOrder(songJiang);

//        System.out.println(guanSheng.getLeft());
//        System.out.println(guanSheng.getRight());

        tree.clueTreePostList();

    }
}