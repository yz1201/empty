package cn.dbdj1201.iconcurrent.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author tyz1201
 * @datetime 2020-06-22 22:32
 **/
@Slf4j(topic = "c.Test062204")
public class Test602204   {

    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            demo(
//                    () -> new AtomicLong(0),
//                    AtomicLong::getAndIncrement
//            );
//        }

        for (int i = 0; i < 5; i++) {
            demo(
                    LongAdder::new,
                    LongAdder::increment
            );
        }


    }

    private static <T> void demo(Supplier<T> addSupplier, Consumer<T> action) {
        T adder = addSupplier.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    action.accept(adder);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();
        log.debug("adder {} cost {}", adder, (end - start) / 1000_000);
    }
}
