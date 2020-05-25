package cn.dbdj1201.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author tyz1201
 * @datetime 2020-05-25 12:45
 **/
public class Test4 {
    public static void main(String[] args) throws NoSuchMethodException {
        // 使用动态代理技术代理ArrayList集合，使 remove 方法能够删除集合中所有与方法参数相同的元素
        Collection<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("a");

        //增强remove方法
        Collection<String> listProxy = (Collection<String>) Proxy.newProxyInstance(arrayList.getClass().getClassLoader(), arrayList.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    //迭代器删除元素
                    if ("remove".equals(method.getName())) {
                        arrayList.removeIf(s -> s.equals(args1[0])); //若等于该参数的值，则删除
                    }
                    return method.invoke(arrayList, args1);
                });
        listProxy.remove("a");
        System.out.println(arrayList);
    }
}
