package cn.dbdj1201.rabbitmq.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtil {
    /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("192.168.0.103");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、visual host
        factory.setVirtualHost("/sc");
        factory.setUsername("rabbitmq");
        factory.setPassword("rabbitmq");
        // 通过工程获取连接
        return factory.newConnection();
    }

}
