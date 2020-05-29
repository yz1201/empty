package cn.dbdj1201.iconcurrent.cap4;

import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-05-26 18:35
 **/
public class Test {
    private static int num =0;
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(Collection.class.getInterfaces()));
//        System.out.println("=================");
//        System.out.println(Arrays.toString(ArrayList.class.getInterfaces()));
//        int i = 1;
//        System.out.println(i);
//        System.out.println(i + "");
//        System.out.println(getI());

        //定义一个原始的字符串
//        String s1 = "国家的绝密文件!";
//        System.out.println("原字符串:"+s1);//原字符串:国家的绝密文件!
//
//        //使用加密器Encoder中的方法encodeToString​把字符串进行加密
//        //Base64.Encoder encoder = Base64.getEncoder();//获取加密器
//        //String s2 = encoder.encodeToString(s1.getBytes());//调用加密的方法
//        String s2 = Base64.getEncoder().encodeToString(s1.getBytes());
//
//        System.out.println("加密后的字符串:"+s2);//加密后的字符串:5Zu95a6255qE57ud5a+G5paH5Lu2IQ==
//
//        //使用解密器Decoder中的方法decode把加密字符串进行解密
//        byte[] bytes = Base64.getDecoder().decode(s2);
//        String s3 = new String(bytes);
//        System.out.println("解密后的字符串:"+s3);//解密后的字符串:国家的绝密文件!

        //校验字符串"abc"作为一组可以出现任意次
        String regex = "(abc)*";
        String str = "";//true
        str = "abc";//true
        str = "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc";//true
        str = "aabbcc";//false
        boolean b1 = str.matches(regex);
        System.out.println("b1:"+b1);

        str = "DG8FV-B9TKY-FRT9J-99899-XPQ4G";//true
        str = "DG8FV-B9TKY-FRTJ-99899-XPQ4G";//false
        str = "DG8FV-B9TKYFRT1J-99899-XPQ4G";//false

        //验证这个序列号：分为5组，每组之间使用-隔开，每组由5位A-Z或者0-9的字符组成
        regex = "([A-Z0-9]{5}-){4}[A-Z0-9]{5}";
        boolean b2 = str.matches(regex);
        System.out.println("b2:"+b2);
    }

    private static int getI(){
        return ++num;
    }
}
