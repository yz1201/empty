package cn.dbdj1201.ds.iHashtable;

/**
 * @author tyz1201
 * @datetime 2020-05-05 16:46
 * 员工链表
 **/
public class EmpLinkedList {
    private Emp head;//头指针

    /*
    添加员工
        1，添加员工时，id是自增长，即id分配为从小到大
           因此直接将节点加入到最后。
     */
    public void add(Emp emp) {
        //如果是第一个员工，即头节点为null
        if (head == null) {
            head = emp;
            return;
        }

        //头节点不为空时，找到最后一个节点，指向新增节点
        Emp cur = head;
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        cur.setNext(emp);
    }


    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "链表是空的，兄弟");
            return;
        }
        Emp cur = head;
        System.out.print("第" + no + "条链表数据为 ");
        while (cur != null) {
            System.out.printf(" =>id=%d name=%s\t", cur.getId(), cur.getName());
            cur = cur.getNext();
        }
        System.out.println();
    }

    public void list() {
        if (head == null) {
            System.out.println("链表是空的，兄弟");
            return;
        }
        Emp cur = head;
        System.out.println("list->" + cur);
        while (cur != null) {
            System.out.println(cur);
            cur = cur.getNext();
        }
    }

    /*
   根据id查询emp
    */
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("it is blank");
            return null;
        }

        Emp cur = head;
        while (cur.getId() != id) {
            if (cur.getNext() == null) {
                return null;
            }
            cur = cur.getNext();
        }
        return cur;
    }
}
