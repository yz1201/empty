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
            TimeUnit.MILLISECONDS.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tpt.stop();
    }

    /*
    两阶段中止模式
     */
    private Thread monitor;
    //停止标记
    private volatile boolean stop;
    //犹豫模式 balking
    private boolean starting = false;

    public synchronized void start() {
        if (starting)
            return;
        starting = true;
        monitor = new Thread(() -> {
            while (true) {
//                Thread current = Thread.currentThread();
//                if (current.isInterrupted()) {
                if (stop) {
                    log.debug("该走了");
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    //若在休眠过程中被打断,则需重置打断标记
//                    current.interrupt();
                }
            }
        }, "监控线程");
        monitor.start();
    }
    public void stop() {
        stop = true;
        monitor.interrupt();
    }

    /*IntelliJ IDEA 2020.1.2 (Ultimate Edition)
Build #IU-201.7846.76, built on June 1, 2020
Licensed to https://zhile.io
You have a perpetual fallback license for this version
Subscription is active until July 8, 2089
Runtime version: 11.0.7+10-b765.53 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10 10.0
GC: ParNew, ConcurrentMarkSweep
Memory: 725M
Cores: 8
Registry: compiler.automake.allow.when.app.running=true
Non-Bundled Plugins: Lombook Plugin, org.jetbrains.kotlin, org.jetbrains.plugins.vue, cn.yiiguxing.plugin.translate*/
}
