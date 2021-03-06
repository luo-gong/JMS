package lg.cn.jmsactivemqconsumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.*;
import java.io.IOException;

@SpringBootTest
class JmsActivemqConsumerApplicationTests {


    /**
     * 发布订阅模式
     * 消费者
     * 注意
     * 1.发布订阅模式必须先打开消费监听，才能接收到消息
     * 2.监听消息时资源不能释放， System.in.read()阻止资源关闭
     */
    @Test
    void pointToPlane() throws JMSException, IOException {
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
        //创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //7.接收消息
       /* while (true) {
            Message message = messageConsumer.receive();//接收消息
            if (message == null) {
                break;
            } else if (message instanceof TextMessage) {
                System.out.println(((TextMessage) message).getText());
            } else if (message instanceof ActiveMQObjectMessage) {
                System.out.println(((ActiveMQObjectMessage) message).getObject());
            } else {
                //等等
            }
        }*/
        //或者设置监听来接收消息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        System.out.println(((TextMessage) message).getText());
                    } else if (message instanceof ActiveMQObjectMessage) {
                        System.out.println(((ActiveMQObjectMessage) message).getObject());
                    } else {
                        //等等
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();//等待输入
        //释放资源
        session.close();
        connection.close();
    }


    /**
     * 点对点模式
     * jms activemq原生实现消费者
     * 注意
     * 1.发布订阅模式必须先打开消费监听，才能接收到消息
     * 2.监听消息时资源不能释放， System.in.read()阻止资源关闭
     *
     * @throws JMSException
     */
    @Test
    void contextLoads() throws JMSException, IOException, Exception {
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
        //创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //7.接收消息
       /* while (true) {
            Message message = messageConsumer.receive();//接收消息
            if (message == null) {
                break;
            } else if (message instanceof TextMessage) {
                System.out.println(((TextMessage) message).getText());
            } else if (message instanceof ActiveMQObjectMessage) {
                System.out.println(((ActiveMQObjectMessage) message).getObject());
            } else {
                //等等
            }
        }*/
        //或者设置监听来接收消息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        System.out.println(((TextMessage) message).getText());
                    } else if (message instanceof ActiveMQObjectMessage) {
                        System.out.println(((ActiveMQObjectMessage) message).getObject());
                    } else {
                        //等等
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();//等待输入
        //释放资源
        session.close();
        connection.close();
    }

}
