package cn.dbdj1201.concurrent.clazz3;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author tyz1201
 * @datetime 2020-04-08 22:33
 **/
public class ConcurrentMapDemo {

    /*
    总结：
        对于map/set的选择使用：
            不加锁： HashMap LinkedHashMap TreeMap（排序）
            加锁：  HashTable Collections.synchronizedXXX，这两个在并发量不大的情况下可以使用，因为效率不高
                   ConcurrentHash ConcurrentSkipListMap(高并发且排序, 插入固然慢，但之后查询效率很高)
                   vector 效率问题，copyonwritelist 读的多写的少的场景下使用监听器队列
                   Queue:
                        ConcurrentLinkedQueue  poll peak
                        BlockingQueue 阻塞式队列
                            LinkedBlockingQueue 无界队列 put add offer take
                            ArrayBlockingQueue 有界队列 take
                            DelayQueue 执行定时任务
                            LinkedTransferQueue  transfer 有容量，没有消费者就阻塞。
                            SynchronusQueue 没有容量，必须有消费者等着消费，不能放在容器储存
     */


    /*消费者先启动，生产者生产一个东西的时候，不扔在队列里，而是直接去找有没有消费者，有的话直接扔给消费者，
 若没有消费者线程，调用transfer()方法就会阻塞，调用add()、offer()、put()方法不会阻塞。*/

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(() -> { //消费者先启动,可以拿走aaa
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
//        strs.transfer("aaa");
        strs.put("aaaa");  //add、offer
//        new Thread(()->{ //消费者在生产者后启动,拿不到aaa，程序阻塞
//            try {
//                System.out.println(strs.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

}
