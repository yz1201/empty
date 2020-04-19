package cn.dbdj1201.ds.linkedlist;

/**
 * @author tyz1201
 * @datetime 2020-04-19 13:16
 **/
public class CircleSingleLinkedList {

    private Boy first = new Boy(-1);

    //创建指定节点个数的环形链表
    public void addBoy(int nums) {
        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                this.first = boy;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    //遍历
    public void showList() {
        Boy cur = this.first;
        while (true) {
            System.out.println(cur);
            if (cur.getNext() == first)
                break;
            cur = cur.getNext();
        }
    }

    /**
     * @param startNo  起始位置
     * @param countNum 第几个出局
     * @param nums     总节点数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (startNo < 1 || startNo > nums)
            System.out.println("出问题了");
        Boy helper = this.first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        //挪到起始位置
        for (int i = 0; i < startNo - 1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }

        //循环剔除选中目标
        while (helper != first) {
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.println(first + " -> " + "out");

            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println(first + " alive ");
    }

}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
