package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author tyz1201
 * @datetime 2020-05-26 16:15
 **/
@Slf4j(topic = "c.ExecutorSell")
public class ExecutorSell {
    public static void main(String[] args) {
        TickWindow window = new TickWindow(1000);
        Random random = new Random();
        List<Integer> amounts = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                while (window.getCount() > 0) {
                    int amount = window.sell(random.nextInt(5) + 1);
                    amounts.add(amount);
//                    log.debug("sell {}",amount);
                }
            }, "seller-" + i).start();
        }


        log.debug("now: {}", window.getCount());
        log.debug("sells: {}", amounts.stream().mapToInt(Integer::intValue).sum());
    }
}
