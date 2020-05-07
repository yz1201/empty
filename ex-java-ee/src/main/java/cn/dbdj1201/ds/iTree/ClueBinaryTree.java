package cn.dbdj1201.ds.iTree;

/**
 * @author tyz1201
 * @datetime 2020-05-06 22:10
 * 中序线索化二叉树
 **/
public class ClueBinaryTree {

    private IHeroNode root;
    private IHeroNode pre = null; //指向前驱节点

    public void setRoot(IHeroNode root) {
        this.root = root;
    }

    public void clueCurNode(IHeroNode node) {
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }

        //每处理一个节点后，让当前节点是下一个节点的前驱节点。
        pre = node;

    }

    /**
     * 中序线索化
     *
     * @param node 当前需要线索化的节点
     */
    public void clueNodes(IHeroNode node) {
        if (node == null)
            return;

        //线索化左子树
        clueNodes(node.getLeft());

        //线索化当前节点
        //先处理前驱节点
        clueCurNode(node);

        //线索化右子树
        clueNodes(node.getRight());
    }

    //遍历中序线索化二叉树
    public void clueInfixList() {
        IHeroNode node = this.root;
        while (node != null) {
            while (node.getLeftType() == 0)
                node = node.getLeft();
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);

            }
            node = node.getRight();
        }

    }

    public void clueNodesPreOrder(IHeroNode node) {
        if (node == null)
            return;

        //线索化当前节点
        //先处理前驱节点
        clueCurNode(node);

        //线索化左子树
        if (node.getLeftType() != 1)
            clueNodesPreOrder(node.getLeft());

        //线索化右子树
        if (node.getRightType() != 1)
            clueNodesPreOrder(node.getRight());
    }

    public void cluePreList() {
        IHeroNode node = this.root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = node.getLeft();
            }

            System.out.println(node);
            node = node.getRight();
        }

    }

    //后续线索化二叉树
    public void clueNodePostOrder(IHeroNode node) {
        if (node == null)
            return;

        //线索化左子树
        clueNodePostOrder(node.getLeft());

        //线索化右子树
        clueNodePostOrder(node.getRight());

        //线索化当前节点
        clueCurNode(node);
    }

    public void clueTreePostList() {
        IHeroNode node = this.root;

        while (node != null && node.getLeftType() == 0) {
            node = node.getLeft();
        }

        while (node != null) {
            //右节点是线索
            if (node.getRightType() == 1) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                //如果上个处理的节点是当前节点的右节点
                if (node.getRight() == pre) {
                    System.out.println(node);
                    if (node == root) {
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                } else {
                    //如果从左节点的进入则找到有子树的最左节点
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0) {
                        node = node.getLeft();
                    }
                }
            }
        }

    }

}
