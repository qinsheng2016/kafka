package com.qinsheng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: qinsheng
 * @Date: 2020/6/28 01:02
 */
public class Consumer {

    public static void main(String[] args) throws Exception{
        // 1. 建立工厂对象，
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );
        //2 从工厂里拿一个连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3 从连接中获取Session(会话)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 从会话中获取目的地(Destination)消费者会从这个目的地取消息
        Queue queue = session.createQueue("hello");
        //从会话中创建消息提供者
        MessageConsumer consumer = session.createConsumer(queue);
        //从会话中创建文本消息(也可以创建其它类型的消息体)

        while (true) {
            TextMessage message = (TextMessage)consumer.receive();
            System.out.println(message.getText());
        }
    }

}
