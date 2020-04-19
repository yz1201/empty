package cn.dbdj1201.ds.linkedlist;

/**
 * @author tyz1201
 * @datetime 2020-04-19 11:54
 * 双向链表
 **/
public class DoubleLinkedList {

    private IINode head = new IINode(new Hero(-1, "头节点", "确实没啥用"));

    public void addNodeWithOrder(IINode node) {
        IINode cur = this.head.next;
        boolean flag = false;

        while (true) {
            if (cur == null) {
                break;
            }

            if (cur.hero.getNo() > node.hero.getNo()) {
                break;
            } else if (cur.hero.getNo() == node.hero.getNo()) {
                flag = true;
                break;
            }
            cur = cur.next;
        }

        if (flag)
            System.out.println("出问题了");
        else {
            node.next = cur;
            node.prev = cur.prev;
            cur.prev.next = node;
            cur.prev = node;
        }
    }

}

class IINode {
    public Hero hero;      //数据域
    public IINode prev;     //指向前一个节点
    public IINode next;     //指向后一个节点


    public IINode(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return "IINode{" +
                "hero=" + hero +
                '}';
    }
}