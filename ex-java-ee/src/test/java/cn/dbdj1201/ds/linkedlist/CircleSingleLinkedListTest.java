package cn.dbdj1201.ds.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-19 13:49
 **/
public class CircleSingleLinkedListTest {

    private CircleSingleLinkedList list = new CircleSingleLinkedList();

    @Test
    public void addBoy() {
        list.addBoy(5);
        list.showList();
    }

    @Test
    public void showList() {
    }

    @Test
    public void countBoy() {
        list.addBoy(5);
        list.countBoy(1, 2, 5);
    }
}