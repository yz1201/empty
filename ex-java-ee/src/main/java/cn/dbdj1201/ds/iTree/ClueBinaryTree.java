package cn.dbdj1201.ds.iTree;

/**
 * @author tyz1201
 * @datetime 2020-05-06 22:10
 * 中序线索化二叉树
 **/
public class ClueBinaryTree {

    private IHeroNode root;
    private IHeroNode pre = null;

    public void setRoot(IHeroNode root) {
        this.root = root;
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

        //线索化当前节点
        //线索化右子树
    }
}
