package lg.cn.jsmactivemqproductor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.*;

@SpringBootTest
class JsmActivemqProductorApplicationTests {

    /**
     * 发布订阅模式
     * 生产者
     * jms原生实现
     */
    @Test
    void pointToPlane() throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.102:61616");
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.打开连接
        connection.start();
        //4.创建会话
        //param1 是否开启事务
        //param2 消息确认机制
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Topic topic = session.createTopic("jsm-activemq-productor-topic");
        //创建消息生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //创建消息
        //1.createTextMessage是创建的文本类型消息
        //2.createStreamMessage
        //3.createObjectMessage
        //4.createMapMessage
        //5.createBytesMessage
        TextMessage textMessage = session.createTextMessage("第四个jms原生消息");
        //发送消息
        messageProducer.send(textMessage);
        //释放资源
        session.close();
        connection.close();
    }

    /**
     * 点对点模式
     * jms原生实现
     */
    @Test
    void contextLoads() throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.102:61616");
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.打开连接
        connection.start();
        //4.创建会话
        //param1 是否开启事务
        //param2 消息确认机制
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue("jsm-activemq-productor-queue");
        //创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //创建消息
        //1.createTextMessage是创建的文本类型消息
        //2.createStreamMessage
        //3.createObjectMessage
        //4.createMapMessage
        //5.createBytesMessage
        TextMessage textMessage = session.createTextMessage("第三个jms原生消息");
        //发送消息
        messageProducer.send(textMessage);
        //释放资源
        session.close();
        connection.close();
    }

}
