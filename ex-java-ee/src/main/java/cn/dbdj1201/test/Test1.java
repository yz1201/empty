package cn.dbdj1201.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tyz1201
 * @datetime 2020-05-25 12:23
 **/
public class Test1 {
    public static void main(String[] args) {
        /*
            111122aaabbbbcc$$d
        设计程序，统计以上字符串中每个字符出现的次数
        */

        //使用map集合存储，字符串中字符为键，由于键唯一，所以只需在存在该字符时初始化数据，在重复出现时value+1;
        String text = "111122aaabbbbcc$$d";
        Map<Character, Integer> result = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (!result.containsKey(c))
                result.put(c, 1);
            else
                result.put(c, result.get(c) + 1);
        }
        System.out.println(result);
    }
}
