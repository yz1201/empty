package cn.dbdj1201.ds.iHashtable;

/**
 * @author tyz1201
 * @datetime 2020-05-05 17:39
 **/
public class IHashTableDemo {
    public static void main(String[] args) {
        Emp emp = new Emp(1, "a");
        EmpLinkedList linkedList = new EmpLinkedList();
        linkedList.add(emp);
        linkedList.list();
    }
}
