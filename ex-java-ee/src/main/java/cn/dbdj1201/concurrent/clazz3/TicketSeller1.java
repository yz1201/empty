package cn.dbdj1201.concurrent.clazz3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author tyz1201
 * @datetime 2020-04-08 22:21
 * 并发性容器解决火车票问题
 **/
public class TicketSeller1 {

    private static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 100; i++) {
            tickets.add("ticket-" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null)
                        break;
                    System.out.println(Thread.currentThread().getName() + " sold " + s);
                }
            }, "consumer-" + i).start();
        }
    }
}
