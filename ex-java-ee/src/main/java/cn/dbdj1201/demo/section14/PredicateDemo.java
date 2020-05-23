package cn.dbdj1201.demo.section14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author tyz1201
 * @datetime 2020-05-23 11:07
 **/
public class PredicateDemo {
    public static void main(String[] args) {
//        test('a', character -> character < 97);
//        test('A', character -> character < 97);
//        test('d', character -> character < 97);

        List<Character> characters = new ArrayList<>();
        characters.add('a');
        characters.add('b');
        characters.add('c');
        characters.add('d');
        characters.add('E');
        if (characters.stream().filter(character -> character>=97).count()==characters.size())
            System.out.println("👆");
        else
            System.out.println("👇");
    }

    private static void test(char c, Predicate<Character> predicate) {
        if (predicate.test(c))
            System.out.println("👆");
        else
            System.out.println("👇");
    }

    private static void test(char c, Predicate<Character>... predicates) {

    }
}
