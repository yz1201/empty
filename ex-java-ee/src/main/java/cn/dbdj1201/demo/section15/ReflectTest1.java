package cn.dbdj1201.demo.section15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author tyz1201
 * @datetime 2020-05-23 16:40
 **/
public class ReflectTest1 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //创建集合
        ArrayList<Integer> array = new ArrayList<>();

//        array.add(10);
//        array.add(20);
//        array.add("hello");

        Class<?> c = array.getClass();
        Method m = c.getMethod("add", Object.class);

        m.invoke(array, "hello");
        m.invoke(array, "world");
        m.invoke(array, "java");

        System.out.println(array);
    }
}
