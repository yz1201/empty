package cn.dbdj1201.ds.linkedlist;

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
        while (temp.next != null) {
            temp = temp.next;
            System.out.println("add -> " + temp);
            if (temp.hero.getNo() == iNode.hero.getNo()) {
                System.out.println("出问题了，有内鬼 same value -> " + temp);
                throw new RuntimeException("hei hei");
            }

            if (temp.hero.getNo() > iNode.hero.getNo()) {
                break;
            }
        }
        iNode.next = temp.next;
        temp.next = iNode;
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