package lg.cn.springbootjmsactivetxproducer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 原生activemq生产者事务
 */
@Component
public class ActivemqTx {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 不开启事务
     * 模拟发送消息失败
     */
    public void sendNoTx() {
        for (int i = 0; i < 5; i++) {
            if (i == 3)
                throw new RuntimeException("测试没有开启事务，抛出异常");
            jmsMessagingTemplate.convertAndSend(new ActiveMQQueue("activemqTX"), "sendNoTx>>" + i);
        }
    }

    /**
     * 模拟开启事务发送消息
     */
    public void sendTx() throws JMSException {
        //1。创建连接工厂
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.开启连接
        connection.start();
        //4.创建会话，并开启事务
        //param1是否开启事务
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //5.创建目的地
        Queue queue = session.createQueue("activemqTX");
        //6.创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue);
        try {
            for (int i = 0; i < 5; i++) {
                if (i == 3)
                    throw new RuntimeException("测试开启事务，抛出异常");
                //7.创建消息（这里创建的时文本消息）
                TextMessage message = session.createTextMessage("sendTx>" + i);
                messageProducer.send(message);
            }
            //8.开启事务后需要commit，否者消息不会到activemq服务器
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //抛出异常者事务回滚
            session.rollback();
        }
        //最后关闭资源
        //。。。。。

    }


}
