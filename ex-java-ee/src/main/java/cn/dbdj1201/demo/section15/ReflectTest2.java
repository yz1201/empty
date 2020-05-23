package cn.dbdj1201.demo.section15;

import org.aspectj.weaver.patterns.IfPointcut;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author tyz1201
 * @datetime 2020-05-23 16:43
 **/
public class ReflectTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //加载数据
        Properties prop = new Properties();
        FileReader fr = new FileReader("F:\\maven_project\\empty\\ex-java-ee\\src\\main\\java\\cn\\dbdj1201\\demo\\section15\\class.txt");
        prop.load(fr);
        fr.close();

        String className = prop.getProperty("className");
        String methodName = prop.getProperty("methodName");
        System.out.println(methodName);

        for (String stringPropertyName : prop.stringPropertyNames()) {
            System.out.println("??????" + stringPropertyName);
        }
        //通过反射来使用
        Class<?> c = Class.forName(className);

        Constructor<?> con = c.getConstructor();
        Object obj = con.newInstance();

        Arrays.stream(c.getDeclaredMethods()).forEach(method -> {
            if (method.getName().equals("show"))
                System.out.println(method);
        });

        Method m = c.getDeclaredMethod(methodName);//study
        System.out.println(m.getName());
        m.setAccessible(true);
        m.invoke(null);
    }
}
