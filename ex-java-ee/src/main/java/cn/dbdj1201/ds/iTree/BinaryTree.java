package cn.dbdj1201.ds.iTree;

import java.util.Objects;

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
        HeroNode guanSheng1 = new HeroNode(6, "guan sheng");
        HeroNode guanSheng2 = new HeroNode(7, "guan sheng");

        songJiang.setLeft(wuYong);
        songJiang.setRight(luJunYi);
        luJunYi.setRight(linChong);
        luJunYi.setLeft(guanSheng);
        wuYong.setLeft(guanSheng1);
        wuYong.setRight(guanSheng2);
        binaryTree.setRoot(songJiang);

        binaryTree.preOrder();
//        System.out.println();
//        binaryTree.infixOrder();
//        System.out.println();
//        binaryTree.postOrder();

//        System.out.println(binaryTree.preOrderSearch(5));
//        System.out.println(binaryTree.infixOrderSearch(5));
//        System.out.println(binaryTree.postOrderSearch(5));

//        binaryTree.delNode(3);
//        System.out.println("==============================");
//        binaryTree.preOrder();
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

    //从根节点进行查找，前序查找
    /*
    先判断当前节点的no是否等于要找的
        如果=，则返回当前节点
        如果！=，则判断当前节点左子节点是否为空，不为空，
    递归前序查找。
        如果左递归查找找到节点，则返回，否则继续判断当前节点的右子节点是否为空，如果不为空则向右递归查找。

     */
    public HeroNode preOrderSearch(int no) {
        if (root != null)
            return root.preOrderSearch(no);
        else
            return null;
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null)
            return root.infixOrderSearch(no);
        else
            return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null)
            return root.postOrderSearch(no);
        else
            return null;
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                if (root.getLeft() == null && root.getRight() == null)
                    root = null;
                else {
                    if (root.getLeft() == null) {
                        root = root.getRight();
                    } else {
                        root = root.getLeft();
                    }
                }
            } else
                root.delNode2(no);
        } else
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

    //根据no进行前序查找

    /**
     * @param no
     * @return 返回该节点或者null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println(":)");
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
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

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
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

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
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
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
