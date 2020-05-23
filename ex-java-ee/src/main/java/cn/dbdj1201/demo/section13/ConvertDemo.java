package cn.dbdj1201.demo.section13;


import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-05-23 10:23
 **/
public class ConvertDemo {
    public static void main(String[] args) {
//        useConverter(num->Integer.parseInt(num));
        useConverter(Integer::parseInt);
        List<Character> characters = new ArrayList<>();
        characters.add('a');
        characters.add('a');
        characters.add('a');
        characters.stream().map((character -> (character + "").toUpperCase())).forEach(System.out::println);
        System.out.println(characters);
    }

    private static void useConverter(Convertable c) {
        int number = c.convert("666");
        System.out.println(number);
    }

}
