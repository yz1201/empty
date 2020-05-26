package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tyz1201
 * @datetime 2020-05-26 16:55
 **/
@Slf4j(topic = "c.ExerciseTransfer")
public class ExerciseTransfer {

    public static void main(String[] args) throws InterruptedException {
        Count c1 = new Count(1000);
        Count c2 = new Count(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c1.transfer(c2, 30);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c2.transfer(c1, 50);
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("c1 money: {}", c1.getMoney());
        log.debug("c2 money: {}", c2.getMoney());

    }
}

class Count {
    private int money;

    public Count(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Count target, int amount) {
        synchronized (Count.class) {
            if (this.money >= amount) {
                this.setMoney(this.money - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}