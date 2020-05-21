package cn.dbdj1201.demo.section576;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author tyz1201
 * @datetime 2020-05-21 14:55
 **/
public class Work1 {
    public static void main(String[] args) {
        clearRepeatedWords("ffaasstt");
    }

    private static void clearRepeatedWords(String str) {
//        Set<Character> set = new HashSet<>();
        List<Character> list  =new ArrayList<>();
        char[] cs = str.toCharArray();

        for (char c : cs) {
            if (!list.contains(c))
                list.add(c);
        }
        System.out.println(list);
    }
}
