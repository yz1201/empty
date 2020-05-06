package cn.dbdj1201.ds.iTree;

import java.util.Objects;

/**
 * @author tyz1201
 * @datetime 2020-05-06 22:17
 **/
public class IHeroNode {

    private int no;
    private String name;
    private IHeroNode left;
    private IHeroNode right;

    //type ---》0表示指向子树，1表示指向前驱/后继节点
    private int leftType;
    private int rightType;

    public IHeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public IHeroNode getLeft() {
        return left;
    }

    public void setLeft(IHeroNode left) {
        this.left = left;
    }

    public IHeroNode getRight() {
        return right;
    }

    public void setRight(IHeroNode right) {
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

    //根据no进行前序查找

    /**
     * @param no
     * @return 返回该节点或者null
     */
    public IHeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }

        IHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null)
            return resNode;
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public IHeroNode infixOrderSearch(int no) {
        IHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null)
            return resNode;

        System.out.println(":)");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    public IHeroNode postOrderSearch(int no) {
        IHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null)
            return resNode;

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null)
            return resNode;

        System.out.println(":)");
        if (this.no == no)
            resNode = this;

        return resNode;  //除了null有其他值？
    }

    //规则，如果是待删除节点为叶子节点，直接删除
    //非叶子节点，则删除该子树
    //思路，找当前节点的某个子节点是否为待删除节点，而不是对当前节点进行判断，这样才能删。
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }

    }

    /*
    规则，如果是待删除节点为叶子节点，直接删除
    非叶子节点，则
        如果待删除节点下只有一个子节点，则用该节点代替删除节点
        有两个子节点，就用左子节点代替。
    思路，找当前节点的某个子节点是否为待删除节点，而不是对当前节点进行判断，这样才能删。
     */
    public void delNode2(int no) {

        if (this.left != null && this.left.no == no) {
            if (this.left.left == null && this.left.right == null)
                this.left = null;
            else {
                this.left = Objects.requireNonNullElseGet(this.left.left, () -> this.left.right);
            }
            return;
        }

        if (this.right != null && this.right.no == no) {
            if (this.right.left == null && this.right.right == null)
                this.left = null;
            else {
                this.right = Objects.requireNonNullElseGet(this.right.left, () -> this.right.right);
            }
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    @Override
    public String toString() {
        return "IHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
