package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-27 21:52
 **/
@Slf4j(topic = "c.P_C")
public class P_CModelDemo {
    //生产者消费者模型
    public static void main(String[] args) {
        MessageQueue mq = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> mq.put(new Message(id, "content-" + id)), "producer-" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("消费呢：{}", mq.take());
            }

        }, "consumer").start();
    }

}

//消息队列类，java线程间通信的消息队列
@Slf4j(topic = "c.MQ")
class MessageQueue {

    private final LinkedList<Message> list = new LinkedList<>();

    private final int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空，暂停售卖");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //
            Message message = list.removeFirst();
            log.debug("已消费消息 :{}", message);
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (list) {
            while (list.size() == this.capacity) {
                try {
                    log.debug("队列已满，暂停生产");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //
            list.addLast(message);
            log.debug("已生产消息：{}", message);
            list.notifyAll();
        }
    }

}

final class Message {
    private int id;
    private String value;

    public Message() {
    }

    public Message(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}