package concurrent.clazz3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-08 22:09
 **/
public class TicketSeller {
    private static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 100000; i++) {
            tickets.add("ticket-" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println(Thread.currentThread().getName() + " sold -> " + tickets.remove(0));
                }
            }, "t-" + i).start();
        }
    }

}
