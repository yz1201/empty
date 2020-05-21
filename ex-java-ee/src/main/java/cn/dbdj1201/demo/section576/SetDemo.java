package cn.dbdj1201.demo.section576;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-21 21:33
 **/
public class SetDemo {
    public static void main(String[] args) {
        LinkedHashSet<String> strings = new LinkedHashSet<>();
        strings.add("a");
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        List<String> list = new ArrayList<>(strings);

        System.out.println(list);
    }
}
