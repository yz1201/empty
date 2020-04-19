package cn.dbdj1201.ds.linkedlist;

/**
 * @author tyz1201
 * @datetime 2020-04-19 11:54
 * 双向链表
 **/
public class DoubleLinkedList {

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