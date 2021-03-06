package cn.dbdj1201.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tyz1201
 * @datetime 2020-04-01 22:12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange", "abc", msg);
        System.out.println("send msg -> " + msg);
        // 等待10秒后再结束
//        Thread.sleep(10000);
    }
}
