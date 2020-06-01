package cn.dbdj1201.iconcurrent.cap4;

import cn.dbdj1201.iconcurrent.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tyz1201
 * @datetime 2020-06-01 9:18
 **/
public class Demo412 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }

}

class Chopstick {
    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread {
    Chopstick left;
    Chopstick right;

    // 卡在这里, 不向下运行
    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private void eat() {
        log.debug("eating...");
        Sleeper.sleep(1);
    }

    @Override
    public void run() {
        while (true) {
// 获得左手筷子
            synchronized (left) {
// 获得右手筷子
                synchronized (right) {
// 吃饭
                    eat();
                }
// 放下右手筷子
            }
// 放下左手筷子
        }
    }
}