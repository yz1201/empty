package cn.dbdj1201.demo.section15;

/**
 * @author tyz1201
 * @datetime 2020-05-23 15:43
 **/
public class ClassloaderDemo {
    public static void main(String[] args) {
        //static ClassLoader getSystemClassLoader()：返回用于委派的系统类加载器
        ClassLoader c = ClassLoader.getSystemClassLoader();
        System.out.println(c); //AppClassLoader

        //ClassLoader getParent()：返回父类加载器进行委派
        ClassLoader c2 = c.getParent();
        System.out.println(c2); //PlatformClassLoader

        ClassLoader c3 = c2.getParent();
        System.out.println(c3); //null
    }
}
