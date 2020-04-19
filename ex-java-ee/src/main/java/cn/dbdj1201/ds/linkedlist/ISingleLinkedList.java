package cn.dbdj1201.ds.linkedlist;

import java.util.Stack;

/**
 * @author tyz1201
 * @datetime 2020-04-17 2:03
 * 包含头节点
 **/
public class ISingleLinkedList {
    /*
    小结：
        1，链表是以节点方式来存储的，是链式存储
        2，每个节点包含data域，next域
        3，链表的各个节点不一定是连续存放的
        4，链表分带头节点的链表和没有头节点的链表
        案例：
     */

    private final INode head = new INode(new Hero(-1, "头节点", "确实没啥用"), null); //头节点

    public INode getHead() {
        return head;
    }

    /**
     * 单链表添加节点
     * 先找到最后一个节点，然后指向新节点
     *
     * @param iNode 新节点
     */
    public void add(INode iNode) {
        INode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = iNode;
        iNode.next = null;
    }

    /**
     * 按照编号/排名添加节点，存在相同编号/排名报错
     *
     * @param iNode
     */
    public void addNodeWithOrder(INode iNode) {
        INode temp = this.head;
        boolean flag = false;  //是否存在相同编号的标记位，默认为false
        while (true) {
            if (temp.next == null)
                break;
            if (temp.next.hero.getNo() > iNode.hero.getNo()) {
                break;
            } else if (temp.next.hero.getNo() == iNode.hero.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("出问题了");
        } else {
            iNode.next = temp.next;
            temp.next = iNode;
        }
    }

    /**
     * 遍历打印当前单链表结构
     */
    public void list() {
        if (this.head.next == null) {
            System.out.println("empty");
            return;
        }

        System.out.println(this.head);
        INode temp = this.head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    /**
     * 返回有效节点个数
     *
     * @return
     */
    public int size() {
        INode cur = this.head;
        int size = 0;
        while (cur.next != null) {
            cur = cur.next;
            size++;
        }
        return size;
    }

    /**
     * 拿到倒数第k个节点
     *
     * @param k
     * @return
     */
    public INode getLastIndexNode(int k) {
        int size = this.size();
        if (k > size)
            System.out.println("出问题了");
        int index = size - k + 1;
        INode temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 逆序打印
     */
    public void printListWithReverseOrder() {
        INode cur = this.head;
        Stack<INode> stack = new Stack<>();
        while (cur.next != null) {
            cur = cur.next;
            stack.push(cur);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 单链表的反转
     *
     * @param head
     */
    public void reverseList(INode head) {
        /*
        没有效节点或者只有一个，不做操作
         */
        if (head.next == null || head.next.next == null) {
            return;
        }

        INode cur = head.next;  //指向第一个节点的指针
        INode next;       //cur的下一个节点
        INode reverseHead = new INode(new Hero()); // 新的头节点

        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 合并两个有序单链表
     * 此处默认单链表都是非空的
     *
     * @param head1
     * @param head2
     */
    public INode join(INode head1, INode head2) {
        INode joinHead = new INode(new Hero(-1, "join head", "没啥用啊"));
        joinHead.next = this.joinList(head1.next, head2.next);
        return joinHead;
    }

    public INode joinList(INode cur1, INode cur2) {
        INode firstNode;
        if (cur1 == null) {
            return cur2;
        }
        if (cur2 == null) {
            return cur1;
        }
        if (cur1.hero.getNo() < cur2.hero.getNo()) {
            firstNode = cur1;
            cur1.next = joinList(cur2, cur1.next);
        } else if (cur1.hero.getNo() > cur2.hero.getNo()) {
            firstNode = cur2;
            firstNode.next = joinList(cur1, cur2.next);
        } else {
            System.out.println("出问题了，不能有相同的吧？");
            firstNode = cur1;
            firstNode.next = joinList(cur1.next, cur1.next);
        }
        return firstNode;
    }

    public static void main(String[] args) {
        INode inode4 = new INode(new Hero(4, "林冲", "豹子头"), null);
        INode inode3 = new INode(new Hero(3, "吴用", "智多星"), null);
        INode inode2 = new INode(new Hero(2, "卢俊义", "玉麒麟"), null);
        INode inode1 = new INode(new Hero(1, "宋江", "及时雨"), null);
        ISingleLinkedList linkedList = new ISingleLinkedList();
//        linkedList.add(inode4);
//        linkedList.add(inode3);
//        linkedList.add(inode2);
//        linkedList.add(inode1);
        linkedList.addNodeWithOrder(inode3);
        linkedList.addNodeWithOrder(inode4);
        linkedList.addNodeWithOrder(inode2);
        linkedList.addNodeWithOrder(inode1);
        linkedList.list();
    }
}

class INode {
    public Hero hero;      //数据域
    public INode next;     //实例域

    public INode(Hero hero) {
        this.hero = hero;
    }

    public INode(Hero hero, INode next) {
        this.hero = hero;
        this.next = next;
    }

    @Override
    public String toString() {
        return "INode{" +
                "hero=" + hero +
                '}';
    }
}