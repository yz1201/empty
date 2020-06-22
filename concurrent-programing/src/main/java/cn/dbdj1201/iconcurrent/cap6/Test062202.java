package cn.dbdj1201.iconcurrent.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author tyz1201
 * @datetime 2020-06-22 19:21
 **/
@Slf4j(topic = "c.Test062202")
public class Test062202 {
    public static void main(String[] args) {
        demo(
                () -> new int[10],
                array -> array.length,
                (array, index) -> array[index]++,
                array -> System.out.println(Arrays.toString(array))
        );

        demo(
                () -> new AtomicIntegerArray(10),
                AtomicIntegerArray::length,
                AtomicIntegerArray::getAndIncrement,
                System.out::println
        );
    }

    private static <T> void demo(
            Supplier<T> arrSupplier,
            Function<T, Integer> lengthFun,
            BiConsumer<T, Integer> putConsumer,
            Consumer<T> printConsumer
    ) {
        List<Thread> ts = new ArrayList<>();
        T array = arrSupplier.get();
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        ts.forEach(Thread::start);
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printConsumer.accept(array);
    }
}
