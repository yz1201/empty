package cn.dbdj1201.ds.iHashtable;

import java.util.HashMap;

/**
 * @author tyz1201
 * @datetime 2020-05-05 17:09
 **/
public class IHashTable {

    public static void main(String[] args) {
        IHashTable hashTable = new IHashTable(7);
        Emp emp = new Emp(1, "a");
        Emp emp2 = new Emp(2, "b");
        Emp emp3 = new Emp(3, "c");
        Emp emp4 = new Emp(4, "d");
        Emp emp5 = new Emp(5, "e");
        Emp emp6 = new Emp(6, "f");
        Emp emp7 = new Emp(7, "g");
        Emp emp8 = new Emp(8, "h");

        hashTable.add(emp);
        hashTable.add(emp2);
        hashTable.add(emp3);
        hashTable.add(emp4);
        hashTable.add(emp5);
        hashTable.add(emp6);
        hashTable.add(emp7);
        hashTable.add(emp8);
//        hashTable.list();
        System.out.println(hashTable.findEmpById(979));
    }

    private EmpLinkedList[] empLinkedLists;
    private int size;

    public IHashTable() {
    }

    public IHashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[this.size];
        for (int i = 0; i < this.size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加员工到散列表中
    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.getId());
        empLinkedLists[empLinkedListNo].add(emp);
    }

    //遍历散列表
    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public Emp findEmpById(int id) {
        return empLinkedLists[hashFun(id)].findEmpById(id);
    }


    //编写散列函数，使用简单取模法
    public int hashFun(int id) {
        return id % size;
    }

}
