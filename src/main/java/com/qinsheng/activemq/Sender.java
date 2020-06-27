package com.qinsheng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: qinsheng
 * @Date: 2020/6/28 00:54
 */
public class Sender {

    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        try {
            Connection connection = connectionFactory.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue queue = session.createQueue("hello");

            MessageProducer producer = session.createProducer(queue);

            for (int i=0; i<100; i++) {
                TextMessage message = session.createTextMessage("msg: " + i);
                producer.send(message);
            }

            connection.close();
            System.out.println("exit");

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
