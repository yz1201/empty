package cn.dbdj1201.demo.section12;

import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-22 20:47
 **/
public class Con_Pro_Demo {
    public static void main(String[] args) {
        Goods goods = new Goods();

        new Thread(new Producer(goods), "pro").start();
        new Thread(new Consumer(goods), "consumer").start();
    }
}

class Goods {
    private int count = 0;

    /*存货少于十件时，生产商品，富余时休息*/
    public void produce() {
        synchronized (this) {
            while (this.count < 10) {
                try {
                    System.out.println("做产品");
                    count += 5;
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("存货量-》" + this.count);
                    if (count >= 10)
                        notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume(int num) {
        while (this.count >= 10) {
            try {
                System.out.println("给咱来一个");
                count -= num;
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("现在还剩-》" + this.count);
                if (this.count < 10)
                    notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true)
            goods.produce();
    }
}

class Consumer implements Runnable {
    private Goods goods;

    public Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            goods.consume(2);
        }
    }
}