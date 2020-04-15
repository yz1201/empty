package cn.dbdj1201.ds;

/**
 * @author tyz1201
 * @datetime 2020-04-15 16:47
 **/
public class StringDemo {
    public static void main(String[] args) {
        String str = "hello java java";
        String newStr = str.replaceAll("java", "spring?");
        System.out.println(newStr);
    }
}
