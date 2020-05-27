package cn.dbdj1201.iconcurrent.cap4;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-05-27 17:48
 **/
@Slf4j(topic = "c.Test052702")
public class Test052702 {
    public static void main(String[] args) {
//        GuardObject guardObject = new GuardObject();
//        new Thread(() -> {
//            log.debug("waiting...");
//            Object o = guardObject.get(1000);
//            System.out.println(o);
//            log.debug("result {}", ClassLayout.parseInstance(o).toPrintable());
//        }, "t1").start();
//
//        new Thread(() -> {
//            log.debug("downloading...");
//            try {
//                TimeUnit.SECONDS.sleep(2);
////                guardObject.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            guardObject.complete(new Object());
//        }, "t2").start();

        for (int i = 0; i < 3; i++) {
            new People().start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("===================" + MailBoxes.getIds());

        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "content-" + id).start();
        }
    }
}

@Slf4j(topic = "c.People")
class People extends Thread {

    @Override
    public void run() {
        //居民。收信就完了
        GuardObject guardedObject = MailBoxes.createGuardedObject();
        log.debug("receive {}:", guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.debug("have receive it {}: content {}", guardedObject.getId(), mail);
    }
}

@Slf4j(topic = "c.Postman")
class Postman extends Thread {
    private int mailId;
    private String mail;

    public Postman() {
    }

    public Postman(int mailId, String mail) {
        this.mailId = mailId;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardObject go = MailBoxes.getGO(this.mailId);
        log.debug("send {}: {}:", mailId, mail);
        go.complete(mail);
    }
}

class MailBoxes {

    private static final Map<Integer, GuardObject> boxes = new ConcurrentHashMap<>();
    private static int id = 1;

    private static synchronized int generateId() {
        return id++;
    }

    public static GuardObject getGO(int id) {
        return boxes.remove(id);
    }

    public static GuardObject createGuardedObject() {
        GuardObject guardObject = new GuardObject(generateId());
        boxes.put(guardObject.getId(), guardObject);
        return guardObject;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

class GuardObject {
    private Object response;

    private int id;

    public GuardObject() {
    }

    public GuardObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object get() {
        synchronized (this) {

            while (this.response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.response;
        }
    }

    public Object get(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            while (this.response == null) {
                if (passedTime >= timeout)
                    break;
                try {
                    this.wait(timeout - passedTime);//虚假唤醒时不要重新等待timeout的时间了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
            return this.response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            System.out.println("product->" + response);
            this.notifyAll();
        }
    }


    @Override
    public String toString() {
        return "GuardObject{" +
                "response=" + response +
                ", id=" + id +
                '}';
    }
}
