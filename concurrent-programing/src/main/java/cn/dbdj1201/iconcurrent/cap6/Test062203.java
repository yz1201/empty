package cn.dbdj1201.iconcurrent.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author tyz1201
 * @datetime 2020-06-22 22:22
 **/
@Slf4j(topic = "c.Test062203")
public class Test062203 {
    public static void main(String[] args) {
        Student0622 student0622 = new Student0622();
        AtomicReferenceFieldUpdater<Student0622, String> name =
                AtomicReferenceFieldUpdater.newUpdater(Student0622.class, String.class, "name");
        log.debug("{}", name.compareAndSet(student0622, null, "lala"));
        log.debug("{}", name.get(student0622));
    }
}

class Student0622 {
    volatile String name;

    @Override
    public String toString() {
        return "Student0622{" +
                "name='" + name + '\'' +
                '}';
    }
}
