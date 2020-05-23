package cn.dbdj1201.demo.section14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tyz1201
 * @datetime 2020-05-23 11:37
 **/
public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("te21321st1");
//        list.add("t12312est2");
//        list.add("te123st3");
//        list.add("test5");
//        list.add("tes213t6");
//        list.add("test71");
//        list.add("test71");

//        Stream<String> skip = list.stream().skip(3);
//        Stream<String> limit = list.stream().limit(3);
//        skip.sorted().forEach(System.out::println);
//        Stream.concat(skip,limit).distinct().forEach(System.out::println);

        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");

        System.out.println(list.stream().mapToInt(Integer::parseInt).sum());

        Stream<String> nameStream = Stream.of("t1", "t2", "t3");
        Stream<Integer> ageStream = Stream.of(1, 2, 3);
        Map<String, String> collect = nameStream.collect(Collectors.toMap(str -> str + "t", str -> str + "sb"));
        collect.entrySet().forEach(System.out::println);

    }
}
