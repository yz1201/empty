package cn.dbdj1201.iconcurrent.cap3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-19 20:28
 **/
@Slf4j(topic = "c.TwoPhaseTermination")
public class TwoPhaseTermination {

    public static void main(String[] args) {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tpt.stop();
    }

    /*
    两阶段中止模式
     */
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("该走了");
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //若在休眠过程中被打断,则需重置打断标记
                    current.interrupt();
                }
            }
        }, "监控线程");
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
