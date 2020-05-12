package cn.dbdj1201.ds.iTree.binarySortTree;

/**
 * @author tyz1201
 * @datetime 2020-05-12 13:22
 **/
public class AVLTree {
    /*
    平衡二叉树（AVL树）：二叉排序树的一种，但他要求左子树高度跟右子树高度差<=1.
    为什么拎出来说？因为对于极端情况，二叉排序树会出现形如单链表的形式，导致，查询效率很差。比如[1,2,3,4,5,6]，根据二叉排序树的构建方式，会变成单链表的形式
    怎么构建AVL树？
        常见实现方法有红黑树，AVL(算法)，替罪羊树，伸展树，Treap等。
        
     AVL树构建过程：左旋转？右旋转？双旋转。
     左子树太长右旋转
     右子树太长左旋转
     但在某些情况下，单旋转不能完成平衡二叉树的转化，比如数列int[] arr={10,11,7,6,8,9}，以及int[] arr={2,1,6,5,7,3}

     */

    public static void main(String[] args) {
        int[] array = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i : array) {
            avlTree.add(new Node4(i));
        }

        avlTree.infixOrder();
        System.out.println(avlTree.root.height());
        System.out.println(avlTree.root.leftHeight());
        System.out.println(avlTree.root.rightHeight());
    }

    private Node4 root;

    public void add(Node4 node4) {
        if (this.root == null)
            root = node4;
        else
            root.add(node4);
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

    public Node4 search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    public Node4 searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }

    /**
     * @param node4
     * @return 删除最小节点，返回最小节点的值
     */
    public int delRightTreeMin(Node4 node4) {
        Node4 target = node4;
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
            Node4 targetNode = search(value);
            if (targetNode == null)
                return;
            //如果当前二叉树只有一个root节点,且是我们要删除的节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node4 parent = searchParent(value);
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
                    if (parent != null) {
                        //判断target是parent的左子节点还是右子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else
                        root = targetNode.left;
                } else {
                    if (parent != null) {
                        if (parent.left.value == value)
                            parent.left = targetNode.right;
                        else
                            parent.right = targetNode.right;
                    } else
                        root = targetNode.right;

                }
            }


        }
    }

    //删除左子树最大节点，即把左子树中最大节点替换到当前位置
    private int delLeftTreeMax(Node4 left) {
        Node4 target = left;
        while (target != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }

}


class Node4 {
    int value;
    Node4 left;
    Node4 right;

    public Node4(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null)
            return 0;
        return right.height();
    }

    //返回以当前节点为根节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        //创建新节点，权为当前根节点的权值
        Node4 newNode = new Node4(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成带你过去节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点值替换成右子节点的值
        this.value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树（左子节点）设置成新节点
        left = newNode;
    }

    //右旋转
    private void rightRotate() {
        Node4 newNode = new Node4(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    /**
     * @param node4
     */
    public void add(Node4 node4) {
        /*
        添加新节点，先跟根节点的value比较
        小于就看左子树情况，无节点就直接挂在左子节点。有就左子树递归添加新节点
        大于等于同左。
         */
        if (node4 == null)
            return;
        if (node4.value < this.value) {
            //如果当前节点的左子节点为空
            if (this.left == null) {
                this.left = node4;
            } else {
                this.left.add(node4);
            }
        } else {
            if (this.right == null)
                this.right = node4;
            else
                this.right.add(node4);
        }

        //每次添加完一个节点，对左右子树高度进行判断
        //如果右子树的高度跟左子树的高度之差大于1，需要左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight())
                right.rightRotate();
            leftRotate();
        } else if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
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
    public Node4 search(int value) {
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
    public Node4 searchParent(int value) {
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