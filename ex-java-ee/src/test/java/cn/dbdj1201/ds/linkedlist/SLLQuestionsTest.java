package cn.dbdj1201.ds.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-18 14:10
 * 几道面试题，关于单链表
 **/
public class SLLQuestionsTest {

    private final ISingleLinkedList linkedList = new ISingleLinkedList();
    private final ISingleLinkedList linkedList1 = new ISingleLinkedList();

    @Before
    public void before() {
        INode inode4 = new INode(new Hero(3, "林冲", "豹子头"), null);
        INode inode3 = new INode(new Hero(7, "吴用", "智多星"), null);
        INode inode2 = new INode(new Hero(5, "卢俊义", "玉麒麟"), null);
        INode inode1 = new INode(new Hero(1, "宋江", "及时雨"), null);
        linkedList.addNodeWithOrder(inode3);
        linkedList.addNodeWithOrder(inode4);
        linkedList.addNodeWithOrder(inode2);
        linkedList.addNodeWithOrder(inode1);

        INode inode5 = new INode(new Hero(2, "林1冲", "豹1子头"), null);
        INode inode6 = new INode(new Hero(4, "吴1用", "智1多星"), null);
        INode inode7 = new INode(new Hero(6, "卢1俊义", "玉1麒麟"), null);
        INode inode8 = new INode(new Hero(8, "宋1江", "及时1雨"), null);
        linkedList1.addNodeWithOrder(inode5);
        linkedList1.addNodeWithOrder(inode6);
        linkedList1.addNodeWithOrder(inode7);
        linkedList1.addNodeWithOrder(inode8);
    }

    @Test
    public void test1() {
        /*
        求单链表节点个数
         */
        //遍历，懂?
        System.out.println("size -> " + linkedList.size());
    }

    @Test
    public void test2() {
        /*
        求单链表中倒数第k个节点
         */
        //节点index size - k，懂？
        System.out.println(this.linkedList.getLastIndexNode(1));
        System.out.println(this.linkedList.getLastIndexNode(2));
        System.out.println(this.linkedList.getLastIndexNode(3));
        System.out.println(this.linkedList.getLastIndexNode(4));

    }

    @Test
    public void test3() {
        /*
        单链表的反转
        新head，重新串联
         */

        linkedList.reverseList(linkedList.getHead());
        linkedList.list();
    }

    @Test
    public void test4() {
        /*
        逆序打印单链表 stack
         */
        linkedList.printListWithReverseOrder();
    }

    @Test
    public void test5() {
        /*
        合并两个有序单链表
         */
        INode cur = linkedList.join(linkedList.getHead(), linkedList1.getHead());
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
}
