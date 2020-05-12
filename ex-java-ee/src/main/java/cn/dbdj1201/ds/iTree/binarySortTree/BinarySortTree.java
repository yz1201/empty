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
        直接删除，找到待删除叶子节点的父节点，判断是左子节点还是右子节点，将parent.left =null或者parent.right = null
    2，删除只有一颗子树的节点
        先找到待删除节点targetNode
        找到其父节点parent
        确定targetNode的子节点是左子节点还是右子节点
        targetNode是parent的左子节点还是右子节点
        若target的子节点是左子节点
            则为parent.left = targetNode.left;        //是parent的左子节点
                或者parent.right = targetNode.left.//是parent的右子节点
        若是右
            parent.left = targetNode.right 或者 parent.right = targetNode.right
    3，删除有两棵子树的节点
        找到targetNode及parent
        从targetNode的右子树找到最小节点
        用临时变量保存最小节点
        删除该节点
        targetNode = temp  //要保证是二叉排序树BST，所有从右边找，且找最小。||或者从左子树找一个最大的，一样的道理。
     */

    public Node3 search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    public Node3 searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }

    /**
     * @param node3
     * @return 删除最小节点，返回最小节点的值
     */
    public int delRightTreeMin(Node3 node3) {
        Node3 target = node3;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);//删除权值最小的节点
        return target.value;
    }

    public void delNode(int value) {
        if (root == null)
            return;
        else {
            Node3 targetNode = search(value);
            if (targetNode == null)
                return;
            //如果当前二叉树只有一个root节点,且是我们要删除的节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node3 parent = searchParent(value);
            //删除叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //是左子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;

                } else if (parent.right != null && parent.right.value == value) {
                    //删除右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除儿女都有的节点
                /*
                删除有两棵子树的节点
                找到targetNode及parent
                从targetNode的右子树找到最小节点
                用临时变量保存最小节点
                删除该节点
                targetNode = temp  //要保证是二叉排序树BST，所有从右边找，且找最小。
                 */
                targetNode.value = delRightTreeMin(targetNode.right);
//                targetNode.value = delLeftTreeMax(targetNode.left);
            } else {
                //删除只有一颗子树的节点
                //判断target有左还是右子节点
                if (targetNode.left != null) {
                    //判断target是parent的左子节点还是右子节点
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.right;
                    }
                } else {
                    if (parent.left.value == value)
                        parent.left = targetNode.right;
                    else
                        parent.right = targetNode.right;
                }
            }


        }
    }

    //删除左子树最大节点，即把左子树中最大节点替换到当前位置
    private int delLeftTreeMax(Node3 left) {
        Node3 target = left;
        while (target != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }

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

    //中序遍历
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();

        System.out.println(this);

        if (this.right != null)
            this.right.infixOrder();
    }

    //查找待删除节点
    public Node3 search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null)
                return this.left.search(value);
            else
                return null;
        } else {
            if (this.right == null)
                return null;
            return this.right.search(value);
        }
    }

    //查找待删除节点的父节点
    public Node3 searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //向左查
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右查
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }

}
