package cn.dbdj1201.iconcurrent.cap3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tyz1201
 * @datetime 2020-05-19 11:41
 **/
@Slf4j(topic = "c.Demo2")
public class Demo2 {
    /*
      多线程竞争cpu，交替运行
            交替执行
        谁先谁后，不受我们控制
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (true)
                log.debug("running");
        }, "t-1").start();

        new Thread(() -> {
            while (true)
                log.debug("running");
        }, "t-2").start();

    }
}
