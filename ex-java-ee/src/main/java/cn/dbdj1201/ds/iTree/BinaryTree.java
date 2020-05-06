package cn.dbdj1201.ds.iTree;

/**
 * @author tyz1201
 * @datetime 2020-05-06 11:13
 **/
public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode songJiang = new HeroNode(1, "song jiang");
        HeroNode wuYong = new HeroNode(2, "wu yong");
        HeroNode luJunYi = new HeroNode(3, "lu jun yi");
        HeroNode linChong = new HeroNode(4, "lin chong");
        HeroNode guanSheng = new HeroNode(5, "guan sheng");

        songJiang.setLeft(wuYong);
        songJiang.setRight(luJunYi);
        luJunYi.setRight(linChong);
        luJunYi.setLeft(guanSheng);
        binaryTree.setRoot(songJiang);

        binaryTree.preOrder();
        System.out.println();
        binaryTree.infixOrder();
        System.out.println();
        binaryTree.postOrder();

    }

    private HeroNode root;//根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //从根节点前序遍历
    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
        else
            System.out.println("出问题了");
    }

    public void infixOrder() {
        if (this.root != null)
            this.root.infixOrder();
        else
            System.out.println("出问题了");
    }

    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
        else
            System.out.println("出问题了");
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);//输出当前节点
        //递归左子树
        if (this.left != null)
            this.left.preOrder();

        //递归右子树
        if (this.right != null)
            this.right.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();

        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
