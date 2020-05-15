package cn.dbdj1201.ds.algorithm.kmp;

import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-14 8:31
 **/
public class KMP {
    /*
    应用场景-字符串匹配问题
        字符串匹配问题：
            1，有一个字符串str1="大尾巴狼 尾巴狼 狼 你好吗 你好啊 你好呀 你好呀你好你很好 尾巴 大尾巴你好哦" 和一个子串str2="你好呀"
            2，现在要判断str1是否包含str2，若存在返回第一次出现的位置，不存在返回-1
     */
    public static void main(String[] args) {

        String s1 = "abcedabcde";
        String s2 = "bcedd";
        int ans = violenceMatch(s1, s2);
//        System.out.println(ans);
//        System.out.println(Arrays.toString(kmpNext("ABA")));

        System.out.println(kmpAlgorithm(s1, s2, kmpNext(s2)));
    }

    //暴力匹配算法
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {//保证不越界
            if (s1[i] == s2[j]) {//当前字符相等，后移
                i++;
                j++;
            } else {
                //匹配失败，重新匹配，原始数组的指针挪i-（j-1）个位置，我寻思这不就后移一位吗？
                //i=i-(j-1);??为啥这么写，犯病了？
                i++;
//                i = i + j;
                j = 0;
            }
        }

        if (j == s2Len)
            return i - j;//因为每次从i-j位置匹配的。

        return -1;
    }

    //kmp Knuth-Morris-Pratt字符串查找算法，简称kmp算法，常用于在一个文本串S内查找一个模式穿P的出现位置，所以用这哥仨的名字命名这个算法

    /**
     * @param s1   源字符串
     * @param s2   子字符串
     * @param next 部分匹配表
     * @return
     */
    public static int kmpAlgorithm(String s1, String s2, int[] next) {

        //从头开始匹配
        for (int i = 0, j = 0; i < s1.length(); i++) {
            //字符不匹配，且指向子串的指针至少大于0
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }
            //若两个字符匹配成功
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            //若子串已经匹配结束
            if (j == s2.length())
                return i + 1 - j;  //为啥+1？因为此时i还未处理，j已经+1
        }
        return -1;
    }

    //获取子串的部分匹配表,背？
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j))
                j = next[j - 1];

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }

            next[i] = j;

        }
        return next;
    }

}
